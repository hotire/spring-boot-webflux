package com.kakao.mis.tire.webflux.reactor.processor;

import org.reactivestreams.Processor;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import lombok.RequiredArgsConstructor;
import reactor.core.CorePublisher;

/**
 * @see reactor.core.publisher.MonoProcessor
 * @see reactor.core.publisher.NextProcessor
 */
@RequiredArgsConstructor
public class MonoProcessorCore<O> implements Processor<O, O> {

    final CorePublisher<? extends O> source;

    /**
     * @see reactor.core.publisher.NextProcessor#connect()
     */
    void connect() {
        final Publisher<? extends O> parent = source;
        parent.subscribe(this);
    }

    @Override
    public void subscribe(final Subscriber<? super O> s) {

    }

    @Override
    public void onSubscribe(final Subscription s) {

    }

    @Override
    public void onNext(final O o) {

    }

    @Override
    public void onError(final Throwable t) {

    }

    @Override
    public void onComplete() {

    }
}
