package com.kakao.mis.tire.webflux.netty.core.transport;

import reactor.core.publisher.Mono;
import reactor.netty.DisposableServer;
import reactor.netty.transport.ServerTransport;

/**
 * @see ServerTransport
 */
public class ServerTransportCore {

    /**
     * @see ServerTransport#bind()
     */
    public Mono<? extends DisposableServer> bind() {
        return Mono.empty();
    }
}
