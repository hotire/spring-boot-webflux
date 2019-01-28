package com.kakao.mis.tire.webflux.example;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.util.function.Supplier;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * https://tech.io/playgrounds/929/reactive-programming-with-reactor-3/StepVerifier
 * Learn how to use StepVerifier to test Mono, Flux or any other kind of Reactive Streams Publisher.
 *
 * @author Sebastien Deleuze
 * @see <a href="http://projectreactor.io/docs/test/release/api/reactor/test/StepVerifier.html">StepVerifier Javadoc</a>
 */
public class Part03StepVerifier {

  void expectFooBarComplete(Flux<String> flux) {
    StepVerifier.create(flux)
                .expectNext("foo")
                .expectNext("bar")
                .verifyComplete();
  }

  void expectFooBarError(Flux<String> flux) {
    StepVerifier.create(flux)
      .expectNext("foo")
      .expectNext("bar")
      .verifyError(RuntimeException.class);
  }

  void expectSkylerJesseComplete(Flux<User> flux) {
    StepVerifier.create(flux)
      .assertNext(user -> assertThat(user.getName()).isEqualTo("swhite"))
      .assertNext(user -> assertThat(user.getName()).isEqualTo("jpinkman"))
      .verifyComplete();
  }

  void expect10Elements(Flux<Long> flux) {
    StepVerifier.create(flux)
      .thenAwait(Duration.ofMinutes(10))
      .expectNextCount(10)
      .verifyComplete();
  }

  void expect3600Elements(Supplier<Flux<Long>> supplier) {
    StepVerifier
      .withVirtualTime(supplier)
      .thenAwait(Duration.ofHours(1))
      .expectNextCount(3600)
      .verifyComplete();
  }

  private void fail() {
    throw new AssertionError("workshop not implemented");
  }

}
