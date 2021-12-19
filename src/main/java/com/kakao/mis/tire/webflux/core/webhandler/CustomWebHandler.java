package com.kakao.mis.tire.webflux.core.webhandler;

import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebHandler;

import reactor.core.publisher.Mono;

/**
 * @see org.springframework.web.reactive.DispatcherHandler
 * @see org.springframework.web.server.handler.WebHandlerDecorator
 */
public class CustomWebHandler implements WebHandler {
    @Override
    public Mono<Void> handle(ServerWebExchange exchange) {
        return Mono.empty();
    }
}
