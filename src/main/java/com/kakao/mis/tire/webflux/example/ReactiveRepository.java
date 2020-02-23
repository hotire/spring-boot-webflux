package com.kakao.mis.tire.webflux.example;

import reactor.core.publisher.Flux;

public class ReactiveRepository<T> {
    public Flux<T> findAll() {
        return Flux.empty();
    }
}
