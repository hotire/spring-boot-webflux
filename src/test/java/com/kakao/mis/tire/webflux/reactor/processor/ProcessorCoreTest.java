package com.kakao.mis.tire.webflux.reactor.processor;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Mono;

class ProcessorCoreTest {

    @Test
    void monoProcessor() {
        // given
        final Mono<String> mono = Mono.create(sink -> sink.success("hello"));
        final Mono<String> processor = mono.toProcessor();

        // when
        processor.subscribe();



    }
}