package com.kakao.mis.tire.webflux.body;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.reactive.function.BodyInserter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BodyInserterContextDecorator implements BodyInserter.Context {
    private final BodyInserter.Context delegate;

    @Override
    public List<HttpMessageWriter<?>> messageWriters() {
        return delegate.messageWriters();
    }

    @Override
    public Optional<ServerHttpRequest> serverRequest() {
        return delegate.serverRequest();
    }

    @Override
    public Map<String, Object> hints() {
        return delegate.hints();
    }
}
