package com.kakao.mis.tire.webflux.websocket.server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/ws/send")
@RequiredArgsConstructor
public class ServerController {

    private final ServerHandler serverHandler;

    @GetMapping
    public void broadcast() {
        serverHandler.broadcast("hello");
    }

}
