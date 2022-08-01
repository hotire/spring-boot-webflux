package com.kakao.mis.tire.webflux.body;

import reactor.core.publisher.Mono;

public interface BodyModifiable {

    default <T, R> Mono<R> doWithModifiedBody() {
        return Mono.empty();
    }

}
