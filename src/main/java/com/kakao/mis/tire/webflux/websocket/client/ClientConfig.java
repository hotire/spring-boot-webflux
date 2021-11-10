package com.kakao.mis.tire.webflux.websocket.client;

import java.net.URI;
import java.time.Duration;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Configuration
public class ClientConfig implements ApplicationRunner {

    @Override
    public void run(final ApplicationArguments args) throws Exception {
        final WebSocketClient webSocketClient = new ReactorNettyWebSocketClient();

        final Client clientOne = new Client();
        final Client clientTwo = new Client();

        clientOne.start(webSocketClient, getURI());
        clientTwo.start(webSocketClient, getURI());

        Mono.delay(Duration.ofSeconds(10))
            .publishOn(Schedulers.boundedElastic())
            .subscribe(value -> {
                clientOne.stop();
                clientTwo.stop();
            });
    }

    private URI getURI() {
        try {
            return new URI("ws://localhost:" + 8080 + "/ws");
        } catch (final Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
