package com.kakao.mis.tire.webflux.reactive;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@Slf4j
public class HooksTest {

  @Test
  public void onNextDropped() {
    Hooks.onNextDropped(o -> {
      log.info("onNextDropped : {}", o);
      assertEquals(1, o);
    });

    Mono.from((s) -> {
      s.onComplete();
      s.onNext(1);
    }).subscribe();
  }


  @Test
  public void onErrorDropped() {
    Hooks.onErrorDropped(throwable -> {
      log.error("onErrorDrop : {}", throwable);
      assertTrue(throwable instanceof IllegalStateException);
    });

    Mono.from((s) -> {
       s.onComplete();
       s.onError(new IllegalStateException("onErrorDropped"));
    })
      .doOnError(e -> {})
      .subscribe();
  }

  @Test
  public void onEachOperator() {
    Hooks.onEachOperator(objectPublisher -> {
        log.info("onEachOperator {}", objectPublisher);
        return objectPublisher;
      });
    StepVerifier.create(Mono.just(1).map(i -> i)).expectNextCount(1).verifyComplete();
  }

  @Test
  public void onOperatorError() {
    Hooks.onOperatorError((throwable, o) -> {
      assertTrue(throwable instanceof RuntimeException);
      assertEquals(1, o);
      return new TestException(throwable);
    });

    Mono<Object> errorMono = Mono.just(1).map(i -> {
      throw new RuntimeException();
    });

    StepVerifier.create(errorMono).expectError(TestException.class).verify();
  }

  private class TestException extends RuntimeException {
    private TestException(Throwable cause) {
      super(cause);
    }
  }

}
