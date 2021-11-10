package com.kakao.mis.tire.webflux;

import reactor.core.publisher.Mono;

import java.util.Optional;

public class Test {

    @org.junit.Test
    public void test() {
        Mono<String> mono = Mono.create(monoSink -> {
            Optional.ofNullable(get()).ifPresent(monoSink::success);
            System.out.println("??");
            monoSink.success();
        });
        mono.subscribe(s -> System.out.println(s));

    }

    public String get() {
        return null;
    }
}
