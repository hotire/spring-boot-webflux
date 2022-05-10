package com.kakao.mis.tire.webflux.reactor;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
class MonoTest {

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
