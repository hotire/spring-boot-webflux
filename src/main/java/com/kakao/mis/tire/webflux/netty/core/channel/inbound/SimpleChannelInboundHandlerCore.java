package com.kakao.mis.tire.webflux.netty.core.channel.inbound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;
import lombok.RequiredArgsConstructor;

/***
 * @see io.netty.channel.SimpleChannelInboundHandler
 */
@RequiredArgsConstructor
public class SimpleChannelInboundHandlerCore<I> {

    private final boolean autoRelease;

    /**
     * @see io.netty.channel.SimpleChannelInboundHandler#channelRead(ChannelHandlerContext, Object)
     */
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        boolean release = true;
        try {
            channelRead0(ctx, (I)msg);
        } finally {
            if (autoRelease && release) {
                ReferenceCountUtil.release(msg);
            }
        }
    }

    protected void channelRead0(ChannelHandlerContext ctx, I msg) throws Exception {

    }
}
