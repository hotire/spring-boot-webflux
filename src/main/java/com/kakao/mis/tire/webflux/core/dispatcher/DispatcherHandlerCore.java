package com.kakao.mis.tire.webflux.core.dispatcher;

import org.springframework.context.ApplicationContext;
import org.springframework.web.reactive.DispatcherHandler;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

/**
 * @see DispatcherHandler
 */
public class DispatcherHandlerCore {

    /**
     * @see DispatcherHandler#initStrategies(ApplicationContext)
     */
    protected void initStrategies(final ApplicationContext context) {
    }

    /**
     * @see DispatcherHandler#handle(ServerWebExchange) 
     */
    public Mono<Void> handle(final ServerWebExchange exchange) {
        return Mono.empty();
    }
}
