package com.kakao.mis.tire.webflux.async;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoSink;

@Slf4j
@RestController
@RequestMapping("/async")
public class AsyncController {
    private final Map<String, MonoSink<ResponseEntity<?>>> map = new ConcurrentHashMap<>();

    @GetMapping("/{id}")
    public Mono<ResponseEntity<?>> mono(@PathVariable final String id) {
        log.info("async : {}", id);
        return Mono.<ResponseEntity<?>>create(sink -> { map.put(id, sink); });
    }

    @GetMapping("/flush/{message}")
    public void flush(@PathVariable final String message) {
        map.values().forEach(sink -> sink.success(ResponseEntity.ok().body(message)));
        map.clear();
    }
}
