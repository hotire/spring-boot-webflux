package com.kakao.mis.tire.webflux.reactor.flux;

import java.util.function.Consumer;

import reactor.core.Disposable;

/**
 * @see reactor.core.publisher.FluxPublish
 */
public class FluxPublishCore {

    /**
     * @see reactor.core.publisher.FluxPublish#connect(Consumer)
     */
    public void connect(final Consumer<? super Disposable> cancelSupport) {

    }
}
