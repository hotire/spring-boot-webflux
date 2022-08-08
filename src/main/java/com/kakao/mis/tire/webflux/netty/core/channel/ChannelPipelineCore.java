package com.kakao.mis.tire.webflux.netty.core.channel;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.DefaultChannelPipeline;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * @see ChannelPipeline
 * @see DefaultChannelPipeline
 */
public class ChannelPipelineCore {

    /**
     * @see ChannelPipeline#fireChannelRegistered()
     * @see DefaultChannelPipeline#fireChannelRegistered()
     */
    public final ChannelPipeline fireChannelRegistered() {
        return null;
    }

    /**
     * @see ChannelPipeline#addLast(EventExecutorGroup, String, ChannelHandler)
     * @see DefaultChannelPipeline#addLast(EventExecutorGroup, String, ChannelHandler)
     *
     * blocking 인 경우 EventExecutorGroup을 함께 넘긴다.
     */
    public final ChannelPipeline addLast(EventExecutorGroup group, String name, ChannelHandler handler) {
        return null;
    }
    
    /**
     * @see DefaultChannelPipeline#newContext(EventExecutorGroup, String, ChannelHandler)
     */
    private ChannelHandlerContext newContext(EventExecutorGroup group, String name, ChannelHandler handler) {
        return null;
    }

    /**
     * @see ChannelPipeline#addLast(String, ChannelHandler)
     * @see DefaultChannelPipeline#addLast(String, ChannelHandler)
     */
    public final ChannelPipeline addList(String name, ChannelHandler handler) {
        return null;
    }
}
