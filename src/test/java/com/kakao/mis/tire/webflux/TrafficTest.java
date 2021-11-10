package com.kakao.mis.tire.webflux;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

class TrafficTest {

    @Test
    void test() throws InterruptedException {
        // given
        final WebClient webClient = WebClient.builder().build();

        IntStream.range(0, 100).forEach(index -> webClient.get()
                                                          .uri("http://localhost:8080/async/" + index)
                                                          .retrieve()
                                                          .bodyToMono(String.class)
                                                          .subscribe(System.out::println));
        Thread.sleep(50000L);
    }
}
