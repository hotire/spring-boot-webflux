package com.kakao.mis.tire.webflux.netty.core.handler.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.ArrayList;
import java.util.List;

/**
 * @see ByteToMessageDecoder
 * @see io.netty.channel.ChannelInboundHandlerAdapter 구현체
 */
public abstract class ByteToMessageDecoderCore {

    /**
     * @see ByteToMessageDecoder#channelRead(ChannelHandlerContext, Object)
     */
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof ByteBuf) {
            callDecode(ctx, (ByteBuf) msg, new ArrayList<>());
        }
    }

    /**
     * @see ByteToMessageDecoder#callDecode(ChannelHandlerContext, ByteBuf, List)
     */
    protected void callDecode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        decodeRemovalReentryProtection(ctx, in, out);
    }

    /**
     * @see ByteToMessageDecoder#decodeRemovalReentryProtection(ChannelHandlerContext, ByteBuf, List)
     */
    final void decodeRemovalReentryProtection(ChannelHandlerContext ctx, ByteBuf in, List<Object> out)
            throws Exception {
        decode(ctx, in ,out);
    }

    /**
     * @see ByteToMessageDecoder#decode(ChannelHandlerContext, ByteBuf, List)
     */
    protected abstract void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception;
}
