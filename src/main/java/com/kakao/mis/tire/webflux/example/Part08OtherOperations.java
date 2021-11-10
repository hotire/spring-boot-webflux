package com.kakao.mis.tire.webflux.example;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * https://tech.io/playgrounds/929/reactive-programming-with-reactor-3/OthersOperations
 * https://projectreactor.io/docs/core/release/reference/index.html#which-operator
 * Learn how to use various other operators.
 *
 * @author Sebastien Deleuze
 */
public class Part08OtherOperations {

    Flux<User> userFluxFromStringFlux(Flux<String> usernameFlux, Flux<String> firstnameFlux, Flux<String> lastnameFlux) {
        return Flux.zip(usernameFlux, firstnameFlux, lastnameFlux).map(tuple -> new User(tuple.getT1(), tuple.getT2(), tuple.getT3()));
    }

    Mono<User> useFastestMono(Mono<User> mono1, Mono<User> mono2) {
        return Mono.first(mono1, mono2);
    }

    /**
     * Cannot resolve method
     */
    Flux<User> useFastestFlux(Flux<User> flux1, Flux<User> flux2) {
//    return Flux.firstEmitting(flux1, flux2);
        return null;
    }

    Mono<Void> fluxCompletion(Flux<User> flux) {
        return flux.ignoreElements().then(Mono.empty());
    }

    Mono<User> nullAwareUserToMono(User user) {
        return Mono.justOrEmpty(user);
    }

    Mono<User> emptyToSkyler(Mono<User> mono) {
        return mono.switchIfEmpty(Mono.just(User.SKYLER));
    }

}
