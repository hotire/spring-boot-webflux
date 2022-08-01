package com.kakao.mis.tire.webflux.schedulers;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

class BoundedElasticSchedulerCoreTest {

    @Test
    void test() {
        final Scheduler scheduler = Schedulers.newBoundedElastic(1, Integer.MAX_VALUE, "test");

        final Mono<Integer> integerMono = Mono.fromSupplier(() -> 1)
                                              .subscribeOn(scheduler)
                                              .subscribeOn(scheduler);

        integerMono.block();
    }

}