package com.kakao.mis.tire.webflux.netty.core.server;

import reactor.core.publisher.Mono;
import reactor.netty.DisposableServer;
import reactor.netty.transport.ServerTransport;

/**
 * @see reactor.netty.http.server.HttpServer
 * @see reactor.netty.transport.ServerTransport
 */
public class HttpServerCore {

    /**
     * @see ServerTransport#bind()
     */
    public Mono<? extends DisposableServer> bind() {
        return Mono.empty();
    }
}
