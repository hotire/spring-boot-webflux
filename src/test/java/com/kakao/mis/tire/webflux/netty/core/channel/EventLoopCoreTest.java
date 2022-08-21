package com.kakao.mis.tire.webflux.netty.core.channel;

import io.netty.channel.DefaultEventLoop;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

class EventLoopCoreTest {

    @Test
    void schedule() throws InterruptedException, ExecutionException {
        // given
        final DefaultEventLoop defaultEventLoop = new DefaultEventLoop();

        // when
        final Future<?> future = defaultEventLoop.schedule(() -> {
            System.out.println("hello");
        }, 1, TimeUnit.SECONDS);

        // no assert
        future.get();
    }
}