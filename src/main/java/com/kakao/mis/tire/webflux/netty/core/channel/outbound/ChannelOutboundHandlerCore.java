package com.kakao.mis.tire.webflux.netty.core.channel.outbound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandler;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.util.ReferenceCountUtil;

/**
 * @see ChannelOutboundHandler
 * @see ChannelOutboundHandlerAdapter
 */
public class ChannelOutboundHandlerCore {

    /**
     * @see ChannelOutboundHandlerAdapter#write(ChannelHandlerContext, Object, ChannelPromise)
     *
     * 전송 레이어에 도달한 메시지는 기록될 때 또는 Channel이 닫힐 떄 자동으로 해제된다.
     *
     */
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        ReferenceCountUtil.release(msg);
        promise.setSuccess();
    }

}
