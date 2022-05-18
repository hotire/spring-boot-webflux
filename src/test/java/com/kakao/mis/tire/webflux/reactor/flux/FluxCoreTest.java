package com.kakao.mis.tire.webflux.reactor.flux;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
class FluxCoreTest {

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
