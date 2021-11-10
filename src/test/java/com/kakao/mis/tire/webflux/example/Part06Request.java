package com.kakao.mis.tire.webflux.example;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 *
 * https://tech.io/playgrounds/929/reactive-programming-with-reactor-3/Request
 * Learn how to control the demand.
 *
 * @author Sebastien Deleuze
 */
public class Part06Request {

    private ReactiveRepository<User> repository = new ReactiveRepository<>();

    StepVerifier requestAllExpectFour(Flux<User> flux) {
        return StepVerifier.create(flux).expectNextCount(4).expectComplete();
    }

    StepVerifier requestOneExpectSkylerThenRequestOneExpectJesse(Flux<User> flux) {
        return StepVerifier.create(flux)
                           .thenRequest(1).expectNext(User.SKYLER)
                           .thenRequest(1).expectNext(User.JESSE).thenCancel();
    }

    Flux<User> fluxWithLog() {
        return repository.findAll().log();
    }

    Flux<User> fluxWithDoOnPrintln() {
        return repository.findAll().doOnSubscribe(subscription -> System.out.println("Starring"))
                         .doOnNext(user -> {
                             System.out.println(user.getFirstname() + " " + user.getLastname());
                         })
                         .doOnComplete(() -> System.out.println("The end!"));
    }
}
