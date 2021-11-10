package com.kakao.mis.tire.webflux.subscriber;

import reactor.netty.NettyInbound;
import reactor.netty.NettyOutbound;
import reactor.netty.channel.ChannelOperations;

/**
 * @see reactor.netty.channel.ChannelOperations
 * @param <INBOUND>
 * @param <OUTBOUND>
 */
public class MyChannelOperations<INBOUND extends NettyInbound, OUTBOUND extends NettyOutbound> extends ChannelOperations<INBOUND, OUTBOUND> {
    protected MyChannelOperations(final ChannelOperations<INBOUND, OUTBOUND> replaced) {
        super(replaced);
    }
}
