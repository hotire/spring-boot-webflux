package com.kakao.mis.tire.webflux;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class GreetingRouter {

    @Bean
    public RouterFunction<ServerResponse> route(final GreetingHandler greetingHandler) {
        return RouterFunctions.route()
                              .GET("/hello", greetingHandler::hello)
                              .GET("/error", greetingHandler::error)
                              .build();
    }
}
