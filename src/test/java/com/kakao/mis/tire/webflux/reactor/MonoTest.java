package com.kakao.mis.tire.webflux.reactor;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
class MonoTest {

    @Test
    void shareDispose() {
        final AtomicInteger cancelled = new AtomicInteger();
        final Mono<Object> cached = Mono.create(s -> {})
                                        .doOnCancel(cancelled::incrementAndGet)
                                        .share();

        final Disposable d1 = cached.subscribe(); // passing an empty consumer (x -> {}) as a parameter here makes the test pass
        final Disposable d2 = cached.subscribe();

        d1.dispose();

        System.out.println(cancelled.get());
        System.out.println(cancelled.get());
    }

    @Test
    void share() {
        final Mono<String> cold = Mono.<String>create(s -> {
            log.info("create mote");
            s.success("hello");
        }).doOnSubscribe(s -> log.info("doOnSubscribe"));

        final Mono<String> hot = cold.share();
        hot.subscribe(log::info);
        hot.subscribe(log::info);
    }

    @Test
    void shareMulti() throws InterruptedException {
        final Mono<String> cold = Mono.<String>create(s -> {
            log.info("create mote");
            s.success("hello");
        }).subscribeOn(Schedulers.boundedElastic())
          .doOnSubscribe(s -> log.info("doOnSubscribe"));

        final Mono<String> hot = cold.share();
        hot.subscribe(log::info);
        hot.subscribe(log::info);

        Thread.sleep(1000L);
    }

}
