package com.kakao.mis.tire.webflux.netty.core.handler.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @see MessageToByteEncoder
 * @see io.netty.channel.ChannelOutboundHandlerAdapter 구현체
 */
public abstract class MessageToByteEncoderCore<I> {

    /**
     * @see MessageToByteEncoder#write(ChannelHandlerContext, Object, ChannelPromise)
     */
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        ByteBuf buf = null;
        @SuppressWarnings("unchecked")
        I cast = (I) msg;
        encode(ctx, cast, buf);
    }

    /**
     * @see MessageToByteEncoder#encode(ChannelHandlerContext, Object, ByteBuf)
     */
    protected abstract void encode(ChannelHandlerContext ctx, I msg, ByteBuf out) throws Exception;
}
