package com.kakao.mis.tire.webflux.netty.core.util.concurrent;

import io.netty.util.concurrent.DefaultPromise;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import static io.netty.util.internal.ObjectUtil.checkNotNull;

/**
 * @see DefaultPromise
 */
public class DefaultPromiseCore {

    /**
     * @see DefaultPromise#notifyListener(EventExecutor, Future, GenericFutureListener)
     */
    protected static void notifyListener(
            EventExecutor eventExecutor, final Future<?> future, final GenericFutureListener<?> listener) {

    }
}
