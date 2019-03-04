package com.kakao.mis.tire.webflux.reactive;

import java.util.function.Consumer;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Mono;

public class MonoBackPressureSubscriber<T> extends BaseSubscriber<Mono<T>> {

  private Integer initRequest;
  private Integer rateRequest;
  private Consumer<T> consumer;
  private Consumer<? super Throwable> errorConsumer;
  private Runnable completeConsumer;

  private MonoBackPressureSubscriber(Integer initRequest, Integer rateRequest,
    Consumer<T> consumer, Consumer<? super Throwable> errorConsumer,
    Runnable completeConsumer) {
    this.initRequest = initRequest;
    this.rateRequest = rateRequest;
    this.consumer = consumer;
    this.errorConsumer = errorConsumer;
    this.completeConsumer = completeConsumer;
  }

  public static<T> MonoBackPressureSubscriber<T> of(Integer initRequest, Integer rateRequest, Consumer<T> consumer) {
    return new MonoBackPressureSubscriber<>(initRequest, rateRequest, consumer, t -> {}, () -> {});
  }

  public static<T> MonoBackPressureSubscriber<T> of(Integer initRequest, Integer rateRequest,
    Consumer<T> consumer, Consumer<? super Throwable> errorConsumer) {
    return new MonoBackPressureSubscriber<>(initRequest, rateRequest, consumer, errorConsumer, ()-> {});
  }

  public static<T> MonoBackPressureSubscriber<T> of(Integer initRequest, Integer rateRequest,
    Consumer<T> consumer, Consumer<? super Throwable> errorConsumer, Runnable completeConsumer) {
    return new MonoBackPressureSubscriber<>(initRequest, rateRequest, consumer, errorConsumer, completeConsumer);
  }

  @Override
  protected void hookOnSubscribe(Subscription subscription) {
    request(initRequest);
  }

  @Override
  protected void hookOnNext(Mono<T> mono) {
    mono.subscribe(consumer,
        error -> {
        errorConsumer.accept(error);
        request(rateRequest);
        },
        () -> {
        completeConsumer.run();
        request(rateRequest);
        });
  }
}
