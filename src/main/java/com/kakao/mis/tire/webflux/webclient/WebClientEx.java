package com.kakao.mis.tire.webflux.webclient;

import org.springframework.boot.autoconfigure.netty.NettyProperties;
import org.springframework.boot.autoconfigure.netty.NettyProperties.LeakDetection;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.util.ResourceLeakDetector;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class WebClientEx {

    private final String url = "http://localhost:8080";

    public WebClientEx() {
        final NettyProperties.LeakDetection leakDetection = LeakDetection.SIMPLE;
        ResourceLeakDetector.setLevel(ResourceLeakDetector.Level.valueOf(leakDetection.name()));
    }

    public Mono<String> retrieve() {
        return WebClient.builder().build().get().uri(url).retrieve().bodyToMono(String.class);
    }

    public Mono<String> exchange(final String path) {
        return WebClient.builder().build().get().uri(url + path)
                        .exchange()
                        .doOnSuccess(it -> log.info("success code : {}", it.statusCode()))
                        .doOnError(it -> log.info("error code : {}", it.getMessage()))
                        .flatMap(it -> it.bodyToMono(String.class));
    }

    public Mono<String> exchangeToMono() {
        return WebClient.builder().build().get().uri(url).exchangeToMono(it -> it.bodyToMono(String.class));
    }
}
