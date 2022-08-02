package com.kakao.mis.tire.webflux.netty.core.channel;

import io.netty.channel.ChannelHandler;

import java.lang.annotation.*;

/**
 * @see ChannelHandler
 */
public class ChannelHandlerCore {


    /**
     * @see ChannelHandler.Sharable
     */
    @Inherited
    @Documented
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @interface SharableCore {
        // no value
    }
}
