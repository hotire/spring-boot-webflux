package com.kakao.mis.tire.webflux.reactor;

import org.reactivestreams.Publisher;

import reactor.core.publisher.Mono;

/**
 * @see reactor.core.publisher.MonoWhen
 */
public class MonoWhenCore {
    
    /**
     * @see reactor.core.publisher.Mono#when(Publisher[])
     */
    public static Mono<Void> when(Publisher<?>... sources) {
        return Mono.when(sources);
    }
}
