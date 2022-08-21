package com.kakao.mis.tire.webflux.netty.core.channel;

import io.netty.channel.DefaultEventLoop;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class EventLoopCoreTest {

    @Test
    void schedule() throws InterruptedException {
        // given
        final DefaultEventLoop defaultEventLoop = new DefaultEventLoop();

        // when
        defaultEventLoop.schedule(() -> {
            System.out.println("hello");
        }, 1, TimeUnit.SECONDS);

        // no assert
        Thread.sleep(1000L);
    }
}