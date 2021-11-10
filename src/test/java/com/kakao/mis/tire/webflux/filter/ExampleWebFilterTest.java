package com.kakao.mis.tire.webflux.filter;

import com.kakao.mis.tire.webflux.GreetingHandler;
import com.kakao.mis.tire.webflux.GreetingRouter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.Assert.assertEquals;

/**
 * https://www.baeldung.com/spring-webflux-filters
 */
@WebFluxTest(value = { GreetingHandler.class, GreetingRouter.class, ExampleWebFilter.class })
class ExampleWebFilterTest {

    @Autowired
    WebTestClient webTestClient;

    @DisplayName("webFilter 테스트")
    @Test
    void assert_webFilter() {
        webTestClient.get()
                     .uri("/hello")
                     .exchange()
                     .expectStatus().isOk()
                     .expectBody(String.class)
                     .isEqualTo("Hello, Spring!")
                     .consumeWith(response ->
                                          assertEquals(response
                                                               .getResponseHeaders()
                                                               .getFirst("web-filter"), "web-filter-test")
                     );
    }

    @DisplayName("filterFunction 테스트")
    @Test
    void assert_filterFunction_forbidden() {
        webTestClient.get()
                     .uri("/user/test")
                     .exchange()
                     .expectStatus().isForbidden();
    }
}