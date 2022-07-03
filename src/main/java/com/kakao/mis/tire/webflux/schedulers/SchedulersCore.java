package com.kakao.mis.tire.webflux.schedulers;

import java.util.concurrent.ThreadFactory;

import reactor.core.scheduler.NonBlocking;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

/**
 * @see Schedulers
 */
public class SchedulersCore {

    /**
     * @see Schedulers#boundedElastic()
     */
    public static Scheduler boundedElastic() {
        return Schedulers.boundedElastic();
    }

    /**
     * @see Schedulers#newBoundedElastic(int, int, ThreadFactory, int)
     */
    public static Scheduler newBoundedElastic(final int threadCap, final int queuedTaskCap, final String name, final int ttlSeconds, final boolean daemon) {
        return Schedulers.newBoundedElastic(threadCap, queuedTaskCap, name, ttlSeconds, daemon);
    }

    /**
     * @see Schedulers#isInNonBlockingThread()
     */
    public static boolean isInNonBlockingThread() {
        return Thread.currentThread() instanceof NonBlocking;
    }
}
