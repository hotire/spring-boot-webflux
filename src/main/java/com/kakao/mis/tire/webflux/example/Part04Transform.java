package com.kakao.mis.tire.webflux.example;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * https://tech.io/playgrounds/929/reactive-programming-with-reactor-3/transform
 *
 * Learn how to transform values.
 *
 * @author Sebastien Deleuze
 */
public class Part04Transform {

  Mono<User> capitalizeOne(Mono<User> mono) {
    return mono.map(user -> new User(user.getUsername().toUpperCase(), user.getFirstname().toUpperCase(), user.getLastname().toUpperCase()));
  }

  Flux<User> capitalizeMany(Flux<User> flux) {
    return flux.map(user -> new User(user.getUsername().toUpperCase(), user.getFirstname().toUpperCase(), user.getLastname().toUpperCase()));
  }

  Flux<User> asyncCapitalizeMany(Flux<User> flux) {
    return flux.flatMap(this::asyncCapitalizeUser);
  }

  Mono<User> asyncCapitalizeUser(User u) {
    return Mono
      .just(new User(u.getUsername().toUpperCase(), u.getFirstname().toUpperCase(), u.getLastname().toUpperCase()));
  }
}
