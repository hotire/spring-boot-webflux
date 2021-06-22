package com.kakao.mis.tire.webflux.rsocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RSocketController {

    @MessageMapping("request-response")
    Message requestResponse(final Message request) {
        log.info("Received request-response request: {}", request);
        // create a single Message and return it
        return new Message();
    }
}
