package com.kakao.mis.tire.webflux;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureWebTestClient
public class WebfluxApplicationTestsMock {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void get() {
        webTestClient.get().uri("/hello").accept(MediaType.TEXT_PLAIN).exchange().expectStatus().isOk().expectBody(String.class).isEqualTo("Hello, Spring!").consumeWith(System.out::print);
    }
}
