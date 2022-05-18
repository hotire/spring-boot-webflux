package com.kakao.mis.tire.webflux.reactor.mono;

import reactor.core.publisher.Mono;

/**
 * @see reactor.core.publisher.Mono
 */
public class MonoCore {

    /**
     * @see Mono#onAssembly(Mono)
     */
    protected static <T> Mono<T> onAssembly(final Mono<T> source) {
        return Mono.empty();
    }
}
