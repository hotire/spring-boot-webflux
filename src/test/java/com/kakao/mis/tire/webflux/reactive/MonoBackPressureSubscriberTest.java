package com.kakao.mis.tire.webflux.reactive;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.Stream;
import org.junit.Before;
import org.junit.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class MonoBackPressureSubscriberTest {

  List<Mono<Integer>> request;

  @Before
  public void config() {
    request = Stream.iterate(1, x -> x + 1).limit(10)
      .map(integer -> this.getMono(integer).subscribeOn(Schedulers.parallel()))
      .collect(toList());
  }

  private Mono<Integer> getMono(int i) {
    return Mono.create(sink -> {
      try {
        Thread.sleep(3000);
        sink.success(i);
      } catch (InterruptedException e) {
        sink.error(e);
      }
    });
  }

  @Test
  public void backPressure() throws InterruptedException {

    Flux.fromStream(request::stream)
      .subscribe(new Subscriber<>() {
      Subscription subscription;
      @Override
      public void onSubscribe(Subscription s) {
        this.subscription = s;
        this.subscription.request(3);
      }

      @Override
      public void onNext(Mono<Integer> mono) {
        mono.subscribe(System.out::println,
          error -> this.subscription.request(1),
          () -> this.subscription.request(1));
      }
      @Override
      public void onError(Throwable t) { }
      @Override
      public void onComplete() { }
    });


    Thread.sleep(10000);

  }

  @Test
  public void backPressure_base() throws InterruptedException {
    Flux.fromStream(request::stream)
      .subscribe(new BaseSubscriber<>() {
        @Override
        protected void hookOnSubscribe(Subscription subscription) {
          this.request(3);
        }

        @Override
        protected void hookOnNext(Mono<Integer> value) {
          value.subscribe(System.out::println,
            error -> this.request(1),
            () -> this.request(1));
        }
      });

    Thread.sleep(10000);
  }

  @Test
  public void backPressure_monoBackPressureSubscriber() throws InterruptedException {
    Flux.fromStream(request::stream)
      .subscribe(MonoBackPressureSubscriber.of(3,
        1,
        System.out::println));

    Thread.sleep(10000);
  }
}