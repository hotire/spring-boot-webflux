package com.kakao.mis.tire.webflux;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class GreetingHandler {

    private final String url = "http://localhost:8080";

    public Mono<ServerResponse> hello(final ServerRequest request) {
        final Mono<ClientResponse> mono = WebClient.builder().build().get().uri(url + "/error")
                                                   .exchange()
                                                   .doOnSuccess(it -> log.info("success code : {}", it.statusCode()))
                                                   .doOnError(it -> log.info("error code : {}", it.getMessage()));
        return mono.flatMap(it -> ServerResponse.status(it.statusCode()).body(it.bodyToFlux(DataBuffer.class), DataBuffer.class));
//                                                .contentType(MediaType.TEXT_PLAIN)
//                             .body(BodyInserters.fromValue("Hello, Spring!"));
    }

    public Mono<ServerResponse> error(final ServerRequest request) {
        try {
            Thread.sleep(2000L);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
        return ServerResponse.status(500).body(BodyInserters.fromValue("hello, Error"));
    }
}
