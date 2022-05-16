package com.kakao.mis.tire.webflux.reactor;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

class MonoFluxTest {

    @Test
    void fluxMonoThen() {
        final Flux<String> flux = Flux.create(fluxSink -> {});
        final Flux<String> flux2 = Flux.create(fluxSink -> {});

        Mono.when(flux.then(), flux2.then())
            .block();
    }

}
