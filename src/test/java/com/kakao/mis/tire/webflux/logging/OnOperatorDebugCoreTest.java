package com.kakao.mis.tire.webflux.logging;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Mono;

class OnOperatorDebugCoreTest {

    @Test
    void hook() {
        Hooks.onOperatorDebug();
        try {
//            final int seconds = LocalTime.now().getSecond();
            final int seconds = 2;
            final Mono<Integer> source;

            if (seconds % 2 == 0) {
                source = Flux.range(1, 10)
                             .map(it -> it + 1)
                             .map(it -> it + 1)
                             .map(it -> it + 1)
                             .map(it -> it + 1)
                             .elementAt(11);
            }
            else if (seconds % 3 == 0) {
                source = Flux.range(0, 4)
                             .elementAt(5);
            }
            else {
                source = Flux.just(1, 2, 3, 4)
                             .elementAt(5);
            }

            source.block();
        }
        finally {
            Hooks.resetOnOperatorDebug();
        }
    }
}