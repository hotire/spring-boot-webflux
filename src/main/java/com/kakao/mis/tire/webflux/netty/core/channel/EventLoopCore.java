package com.kakao.mis.tire.webflux.netty.core.channel;

import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * @see java.util.concurrent.ScheduledExecutorService
 * @see EventExecutorGroup
 * @see EventLoopGroup
 * @see EventLoop
 * @see io.netty.channel.nio.NioEventLoop
 */
public interface EventLoopCore {

    /**
     * @see EventLoop#parent()
     * @see
     */
    EventLoopGroup parent();


    /**
     * @see EventLoop#execute(Runnable)
     * @see io.netty.channel.SingleThreadEventLoop#execute(Runnable)
     */
    void execute(Runnable task);

    /**
     * @see EventLoop#inEventLoop()
     */
    boolean inEventLoop();
}
