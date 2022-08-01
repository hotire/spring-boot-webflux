package com.kakao.mis.tire.webflux.reactor.mono;

import org.reactivestreams.Subscriber;

import reactor.core.publisher.Mono;

/**
 * @see reactor.core.publisher.Mono
 */
public class MonoCore<T> {

    /**
     * @see Mono#subscribe(Subscriber)
     * @see Mono#subscribe()
     * @param actual
     */
    public final void subscribe(final Subscriber<? super T> actual) {
    }

    /**
     * @see Mono#onAssembly(Mono)
     */
    protected static <T> Mono<T> onAssembly(final Mono<T> source) {
        return Mono.empty();
    }
}
