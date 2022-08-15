package com.kakao.mis.tire.webflux.netty.core.channel;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoop;

import java.net.SocketAddress;

/**
 * @see io.netty.channel.Channel
 */
public interface ChannelCore {

    /**
     * @see Channel#eventLoop()
     *
     * Channel 에 할당된 EventLoop 반환
     */
    EventLoop eventLoop();

    /**
     * @see Channel#pipeline()
     *
     * Channel 에 할당된 ChannelPipeline 반환
     */
    ChannelPipeline pipeline();

    /**
     * @see Channel#isActive()
     *
     * Channel 이 활성 상태일 때 true /
     * 활성 상태 : 전송의 의미에 따라 활성의 의미가 달라진다. ex) Socket 전송은 원격 피어로 연결되면 활성 상태, Datagram 전송은 열리면 활성상태
     */
    boolean isActive();

    /**
     * @see Channel#localAddress()
     *
     * 로컬 SocketAddress 반환
     */
    SocketAddress localAddress();

    /**
     * @see Channel#remoteAddress()
     *
     * 원격 SocketAddress 반환
     */
    SocketAddress remoteAddress();

    /**
     * @see Channel#write(Object)
     *
     * 데이터를 원력 피어로 출력한다. ChannelPipeline 으로 전달되며 플러시되기 전까지 큐에 저장된다.
     */
    ChannelFuture write();


    /**
     * @see Channel#flush()
     *
     * 기반 전송 ex) socket 으로 출력된 데이터를 플러시한다.
     */
    Channel flush();

}
