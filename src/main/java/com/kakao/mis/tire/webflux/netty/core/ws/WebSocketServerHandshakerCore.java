package com.kakao.mis.tire.webflux.netty.core.ws;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;


/**
 * @see WebSocketServerHandshaker
 */
public class WebSocketServerHandshakerCore {

    /**
     * @see WebSocketServerHandshaker#handshake(Channel, HttpRequest, HttpHeaders, ChannelPromise) 
     */
    public final ChannelFuture handshake(final Channel channel, HttpRequest req,
                                         final HttpHeaders responseHeaders, final ChannelPromise promise) {
        return null;
    }
}
