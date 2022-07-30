package com.kakao.mis.tire.webflux.webclient;

import java.util.function.Function;

import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;

import reactor.core.publisher.Mono;

/**
 * @see org.springframework.web.reactive.function.client.WebClient
 * @see org.springframework.web.reactive.function.client.DefaultWebClient
 */
public class WebClientCore {

    /**
     * @see RequestBodySpec#retrieve()
     */
    public Mono<String> retrieve() {
        return Mono.empty();
    }

    /**
     * @see RequestBodySpec#exchange()
     * @see org.springframework.web.reactive.function.client.DefaultWebClient.DefaultRequestBodyUriSpec#exchange()
     */
    public Mono<String> exchange(final String path) {
        return Mono.empty();
    }

    /**
     * @see RequestBodySpec#exchangeToMono(Function)
     */
    public Mono<String> exchangeToMono() {
        return Mono.empty();
    }
}
