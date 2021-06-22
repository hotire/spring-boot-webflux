package com.kakao.mis.tire.webflux.websocket.server;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoSink;

@Slf4j
@Component
public class ServerHandler implements WebSocketHandler {

    private final Map<String, MonoSink<String>> map = new ConcurrentHashMap<>();
    private final Map<String, WebSocketSession> map2 = new ConcurrentHashMap<>();

    public void send(final String message) {
        map2.values().forEach(session -> {
            session.send(Mono.fromCallable(() -> session.textMessage(message)))
                   .doFinally(s -> log.info("Server -> sent: [{}] to client id=[{}]", message, session.getId()))
                   .subscribe();
        });
    }

    @Override
    public Mono<Void> handle(final WebSocketSession session) {
        return session.receive()
                      .doOnNext(message -> {
                          log.info("Server -> client connected id=[{}]", session.getId());
                        })
                      .map(WebSocketMessage::getPayloadAsText)
                      .doOnNext(message -> log.info("Server -> received from client id=[{}]: [{}]", session.getId(), message))
                      .flatMap(message -> send(session))
                      .doFinally(signalType -> {
                          log.info("Terminating WebSocket Session (client side) sig: [{}], [{}]", signalType.name(), session.getId());
                          session.close();
                          map2.remove(session.getId());
                      })
                      .then();
    }

    private Flux<Void> send(final WebSocketSession session) {
        map2.put(session.getId(), session);
        return Flux.empty();
//        return Flux.interval(Duration.ofMinutes(1))
//                   .map(value -> Long.toString(value))
//                   .flatMap(message -> session.send(Mono.<String>create(sink -> map.put(session.getId(), sink)).map(session::textMessage))
//                                              .then(Mono.fromRunnable(() -> log.info("Server -> sent: [{}] to client id=[{}]", message, session.getId()))));
    }
}
