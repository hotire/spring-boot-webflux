package com.kakao.mis.tire.webflux.example;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

/**
 *
 * https://tech.io/playgrounds/929/reactive-programming-with-reactor-3/Adapt
 *
 * Learn how to adapt from/to RxJava 2 Observable/Single/Flowable and Java 8+ CompletableFuture.
 *
 * Mono and Flux already implements Reactive Streams interfaces so they are natively
 * factory methods.
 *
 * For RxJava 2, you should not use Reactor Adapter but only RxJava 2 and Reactor Core.
 *
 * @author Sebastien Deleuze
 */
public class Part09Adapt {

  ReactiveRepository<User> repository = new ReactiveRepository();

  Flowable<User> fromFluxToFlowable(Flux<User> flux) {
    return Flowable.fromPublisher(flux);
  }

  Flux<User> fromFlowableToFlux(Flowable<User> flowable) {
    return Flux.from(flowable);
  }

  Observable<User> fromFluxToObservable(Flux<User> flux) {
    return Observable.fromPublisher(flux);
  }

  Flux<User> fromObservableToFlux(Observable<User> observable) {
    return Flux.from(observable.toFlowable(BackpressureStrategy.BUFFER));
  }

  Single<User> fromMonoToSingle(Mono<User> mono) {
    return Single.fromPublisher(mono);
  }

  Mono<User> fromSingleToMono(Single<User> single) {
    return Mono.from(single.toFlowable());
  }

  CompletableFuture<User> fromMonoToCompletableFuture(Mono<User> mono) {
    return mono.toFuture();
  }

  Mono<User> fromCompletableFutureToMono(CompletableFuture<User> future) {
    return Mono.fromFuture(future);
  }
}