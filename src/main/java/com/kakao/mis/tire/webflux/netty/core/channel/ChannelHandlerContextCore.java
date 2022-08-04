package com.kakao.mis.tire.webflux.netty.core.channel;

import io.netty.channel.ChannelHandlerContext;

/**
 * @see ChannelHandlerContext
 */
public interface ChannelHandlerContextCore {

    /**
     * @see ChannelHandlerContext#read()
     * @see io.netty.channel.AbstractChannelHandlerContext#read()
     */
    ChannelHandlerContext read();
    
    /**
     * @see ChannelHandlerContext#fireChannelRead(Object) ()
     * @see io.netty.channel.AbstractChannelHandlerContext#fireChannelRead(Object) 
     */
    ChannelHandlerContext fireChannelRead(Object msg);
}
