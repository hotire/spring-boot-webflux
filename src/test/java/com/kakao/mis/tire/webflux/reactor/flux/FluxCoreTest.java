package com.kakao.mis.tire.webflux.reactor.flux;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

@Slf4j
class FluxCoreTest {
    @Test
    void replay() throws InterruptedException {

        final Flux<Integer> when = Flux.range(1, 10);

        final Flux<String> coldSource = Flux.<String>fromStream(() -> {
            System.out.println("create flux");
            final Random random = new Random();
            return List.of(String.valueOf(random.nextInt()), String.valueOf(random.nextInt())).stream();
        }).repeatWhen(longFlux -> Flux.interval(Duration.ofMillis(100)).take(1))
          .doOnSubscribe(s -> log.info("doOnSubscribe"));

        final var hot = coldSource.replay(1).refCount(1);
//        final var hot = coldSource;

        hot.log().subscribe(s -> log.info("out {}", s));
//        hot.log().subscribe();
//        hot.log().subscribe();
        Thread.sleep(100L);
        Thread.sleep(10000L);
//        hot.log().subscribe();
//        hot.log().subscribe();

        hot.publishOn(Schedulers.boundedElastic()).log()
           .subscribe();
//        hot.publishOn(Schedulers.boundedElastic()).log()
//           .subscribe();
//        hot.subscribeOn(Schedulers.boundedElastic()).log()
//           .subscribe();
//        hot.subscribeOn(Schedulers.boundedElastic()).log()
//           .subscribe();
//
//        Mono.create(s -> {
//            s.success(1);
//        }).subscribeOn(Schedulers.boundedElastic()).subscribe(s -> hot.log().subscribe());
//
//        Mono.create(s -> {
//            s.success(1);
//        }).subscribeOn(Schedulers.boundedElastic()).subscribe(s -> hot.log().subscribe());

        Thread.sleep(3000L);
    }

    @Test
    void cold() throws InterruptedException {
        final AtomicReference<FluxSink<String>> sink = new AtomicReference<>();
        final var cold = Flux.<String>push(s -> {
            System.out.println("init");
            sink.set(s);
        });

        cold.subscribe();
        System.out.println("start");
        sink.get().next("connect");
        cold.subscribe(log::info);
        sink.get().complete();
        cold.subscribe(log::info);
    }

    @Test
    void test() throws InterruptedException {
        final AtomicReference<FluxSink<String>> sink = new AtomicReference<>();
        final var hot = Flux.<String>push(s -> {
            System.out.println("init");
            sink.set(s);
        }).share();

//        hot.connect();
        hot.subscribe();
        Thread.sleep(1L);
        System.out.println("start");
        sink.get().next("connect");

        hot.subscribe(log::info);
        hot.subscribe(log::info);
        hot.subscribe(log::info);
        hot.subscribe(log::info);
    }

    @Test
    void share() throws InterruptedException {
        final Flux<String> coldSource = Flux.interval(Duration.ofMillis(1))
                                            .map(Object::toString)
                                            .take(10)
                                            .doOnSubscribe(s -> log.info("doOnSubscribe"));

        final Flux<String> hot = coldSource.share();
        hot.subscribe(log::info);
        hot.subscribe(log::info);
        Thread.sleep(3);
        hot.subscribe(log::info);
        hot.subscribe(log::info);
    }
    @Test
    void shareMulti() throws InterruptedException {
        final Flux<String> coldSource  = Flux.interval(Duration.ofMillis(1))
                                             .map(Object::toString)
                                             .take(10)
                                             .subscribeOn(Schedulers.boundedElastic())
                                             .doOnSubscribe(s -> log.info("doOnSubscribe"));

        final Flux<String> hot = coldSource.share();
        hot.subscribe(log::info);
        hot.subscribe(log::info);
        Thread.sleep(3);
        hot.subscribe(log::info);
        hot.subscribe(log::info);

        Thread.sleep(1000L);
    }
}
