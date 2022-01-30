package com.kakao.mis.tire.webflux.webclient;

import org.junit.jupiter.api.Test;

class WebClientExTest {

    @Test
    void retrieve() {
        final WebClientEx ex = new WebClientEx();
        ex.retrieve().block();
    }

    @Test
    void exchange() {
        final WebClientEx ex = new WebClientEx();
        ex.exchange("/error")
          .log()
          .block();
    }

    @Test
    void exchangeToMono() {
    }
}