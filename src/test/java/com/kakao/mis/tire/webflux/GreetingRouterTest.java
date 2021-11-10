package com.kakao.mis.tire.webflux;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.TestConstructor.AutowireMode;
import org.springframework.test.web.reactive.server.WebTestClient;

import lombok.RequiredArgsConstructor;

@WebFluxTest
@RequiredArgsConstructor
@TestConstructor(autowireMode = AutowireMode.ALL)
class GreetingRouterTest {

    private final WebTestClient testClient;

    @Test
    void test() {
        testClient.get();
    }
}