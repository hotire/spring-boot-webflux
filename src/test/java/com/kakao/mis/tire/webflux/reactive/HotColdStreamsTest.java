package com.kakao.mis.tire.webflux.reactive;

import java.time.Duration;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Test;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/**
 * https://projectreactor.io/docs/core/snapshot/reference/#reactor.hotCold
 */
@Slf4j
public class HotColdStreamsTest {

  @Test
  public void assert_daemon() throws InterruptedException {
    Mono<Object> mono = Mono.create(monoSink -> {
      System.out.println("cache");
      monoSink.success(Lists.newArrayList("a", "b"));
    }).cache(Duration.ofSeconds(3));

    mono.subscribe(list -> log.info(list.toString()));
    mono.subscribe(list -> log.info(list.toString()));

    Thread.sleep(1000);

    mono.subscribe(list -> log.info(list.toString()));

    Thread.sleep(3000);
  }


  /**
   * Most other hot publishers in Reactor extend Processor.
   */
  @Test
  public void hotStreams() {
    DirectProcessor<String> hotSource = DirectProcessor.create();
    Flux<String> hotFlux = hotSource.map(String::toUpperCase);

    hotFlux.subscribe(d -> System.out.println("Subscriber 1 to Hot Source: "+d));

    hotSource.onNext("blue");
    hotSource.onNext("green");

    hotFlux.subscribe(d -> System.out.println("Subscriber 2 to Hot Source: "+d));

    hotSource.onNext("orange");
    hotSource.onNext("purple");
    hotSource.onComplete();
  }

  @Test
  public void coldStreams() {
    Flux<String> source = Flux.fromIterable(Arrays.asList("blue", "green", "orange", "purple")).doOnSubscribe(s-> {
      System.out.println("subscribed to source");
    });
    Flux<String> map = source.map(String::toUpperCase);


    map.subscribe(d -> System.out.println("Subscriber 1: "+d));
    map.subscribe(d -> System.out.println("Subscriber 2: "+d));
  }


  @Test
  public void connect() {
    Flux<Integer> coldSource = Flux.range(1, 3)
      .doOnSubscribe(s -> System.out.println("subscribed to source"));

    ConnectableFlux<Integer> hot = coldSource.publish();

    hot.subscribe(System.out::println);
    hot.subscribe(System.out::println);

    System.out.println("done subscribing");
    System.out.println("will now connect");

    hot.connect();
  }

  @Test
  public void autoConnect()  {
    Flux<Integer> coldSource = Flux.range(1, 3)
      .doOnSubscribe(s -> System.out.println("subscribed to source"));

    Flux<Integer> hot = coldSource.publish().autoConnect(2);

    System.out.println("subscribed first");
    hot.subscribe(System.out::println);
    System.out.println("subscribing second");
    hot.subscribe(System.out::println);

  }

  @Test
  public void mono_just_hot() throws InterruptedException {
    Mono<String> mono = Mono.just(getUser());
    mono.subscribe(log::info);
    mono.subscribe(log::info);
    mono.subscribe(log::info);
  }


  public String getUser() {
    log.info("hello");
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return "hotire";
  }

  @Test
  public void mono_cache_hot() throws InterruptedException {
    Mono<String> mono = Mono
      .create(monoSink -> monoSink.success(getUser()));

    mono = mono.cache(Duration.ofSeconds(3));

    mono.subscribe(log::info);
    mono.subscribe(log::info);
    mono.subscribe(log::info);
  }



}