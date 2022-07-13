package com.kakao.mis.tire.webflux.netty.core.channel;

import io.netty.channel.EventLoopGroup;
import io.netty.util.NettyRuntime;
import io.netty.util.internal.SystemPropertyUtil;

/**
 * @see EventLoopGroup
 * @see io.netty.channel.nio.NioEventLoopGroup
 * @see io.netty.util.concurrent.MultithreadEventExecutorGroup
 * @see io.netty.channel.MultithreadEventLoopGroup
 */
public class EventLoopGroupCore {

    private static final int DEFAULT_EVENT_LOOP_THREADS;

    static {
        DEFAULT_EVENT_LOOP_THREADS = Math.max(1, SystemPropertyUtil.getInt(
                "io.netty.eventLoopThreads", NettyRuntime.availableProcessors() * 2));
    }
}
