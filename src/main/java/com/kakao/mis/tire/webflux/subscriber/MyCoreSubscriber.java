package com.kakao.mis.tire.webflux.subscriber;

import org.reactivestreams.Subscription;

import reactor.core.CoreSubscriber;

/**
 * @see reactor.core.CoreSubscriber
 * Subscriber diff currentContext
 * @param <T>
 */
public class MyCoreSubscriber<T> implements CoreSubscriber<T> {
    @Override
    public void onSubscribe(final Subscription s) {

    }

    @Override
    public void onNext(final Object o) {

    }

    @Override
    public void onError(final Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }
}
