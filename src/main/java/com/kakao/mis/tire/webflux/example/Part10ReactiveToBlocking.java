package com.kakao.mis.tire.webflux.example;

import reactor.core.Exceptions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * https://tech.io/playgrounds/929/reactive-programming-with-reactor-3/ReactiveToBlocking
 * Learn how to turn Reactive API to blocking one.
 *
 * @author Sebastien Deleuze
 */
public class Part10ReactiveToBlocking {

    User monoToValue(Mono<User> mono) {
        return mono.doOnError(throwable -> {
            throw Exceptions.propagate(throwable);
        }).block();
    }

    Iterable<User> fluxToValues(Flux<User> flux) {
        return flux.toIterable();
    }
}
