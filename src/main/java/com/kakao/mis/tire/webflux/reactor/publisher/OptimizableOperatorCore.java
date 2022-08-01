package com.kakao.mis.tire.webflux.reactor.publisher;

import reactor.core.CoreSubscriber;

/**
 * @see reactor.core.publisher.OptimizableOperator
 * @see reactor.core.publisher.InternalMonoOperator
 * @see reactor.core.publisher.InternalFluxOperator
 */
public class OptimizableOperatorCore<IN, OUT> {

    /**
     * @see reactor.core.publisher.OptimizableOperator#subscribeOrReturn(CoreSubscriber)
     */
    public CoreSubscriber<? super OUT> subscribeOrReturn(final CoreSubscriber<? super IN> actual) throws Throwable {
        return null;
    }
}
