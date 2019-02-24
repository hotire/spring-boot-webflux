package com.kakao.mis.tire.webflux.reactive;

import static com.kakao.mis.tire.webflux.reactive.Operators.fromIterable;
import static com.kakao.mis.tire.webflux.reactive.Operators.logSub;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import reactor.core.publisher.Flux;

@Slf4j
public class Schedulers {
  public static void main(String ... args) throws InterruptedException {
    Publisher<Integer> publisher = (realSubOn(fromIterable(IntStream.range(1,10).boxed().collect(toList()))));
    publisher.subscribe(logSub());
    log.debug("exit");
  }

  /**
   * Typically used for slow publisher
   */
  public static<T> Publisher<T> subOn(Publisher<T> pub) {
    return subscriber -> {
      ExecutorService es = Executors
        .newSingleThreadExecutor(new CustomizableThreadFactory("subOn"));

      es.execute(()-> pub.subscribe(new Subscriber<T>() {
        @Override
        public void onSubscribe(Subscription subscription) {
          subscriber.onSubscribe(subscription);
        }

        @Override
        public void onNext(T t) {
          subscriber.onNext(t);
        }

        @Override
        public void onError(Throwable throwable) {
          subscriber.onError(throwable);
          es.shutdown();
        }

        @Override
        public void onComplete() {
          subscriber.onComplete();
          es.shutdown();
        }
      }));
    };
  }

  /**
   * Typically used for fast publisher, slow consumers
   */
  public static<T> Publisher<T> pubOn(Publisher<T> pub) {
    return subscriber -> pub.subscribe(new Subscriber<>() {
      private final ExecutorService es = Executors.
        newSingleThreadExecutor(new CustomizableThreadFactory("pubOn"));

      @Override
      public void onSubscribe(Subscription subscription) {
        subscriber.onSubscribe(subscription);
      }

      @Override
      public void onNext(T t) {
        es.execute(() -> subscriber.onNext(t));
      }

      @Override
      public void onError(Throwable throwable) {
        es.execute(() -> subscriber.onError(throwable));
        es.shutdown();
      }

      @Override
      public void onComplete() {
        es.execute(subscriber::onComplete);
        es.shutdown();
      }
    });
  }

  private static List<Integer> getList() throws InterruptedException {
    Thread.sleep(3000);
    return new ArrayList<>(Arrays.asList(1,2,3,4,5));
  }

  private static void test () throws InterruptedException {
    Flux result = Flux.defer(() -> {
      try {
        log.debug("test!!!");
        return Flux.fromStream(getList().stream());
      } catch (InterruptedException e) {
        e.printStackTrace();
        return Flux.empty();
      }
    }).subscribeOn(reactor.core.scheduler.Schedulers.elastic());
    result.log().subscribe();

    log.debug("exit");
    Thread.sleep(4000);
  }
}
