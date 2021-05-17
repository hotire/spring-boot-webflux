package com.kakao.mis.tire.webflux.error;

import java.util.Map;

import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ErrorAttributesDecorator implements ErrorAttributes {

    private final ErrorAttributes delegate;

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
        return delegate.getErrorAttributes(request, includeStackTrace);
    }

    @Override
    public Throwable getError(ServerRequest request) {
        return delegate.getError(request);
    }

    @Override
    public void storeErrorInformation(Throwable error, ServerWebExchange exchange) {
        delegate.storeErrorInformation(error, exchange);
    }
}
