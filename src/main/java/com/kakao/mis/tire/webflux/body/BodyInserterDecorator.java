package com.kakao.mis.tire.webflux.body;

import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.web.reactive.function.BodyInserter;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class BodyInserterDecorator<T, M extends ReactiveHttpOutputMessage> implements BodyInserter<T, M> {
    private final BodyInserter<T, M> delegate;

    @Override
    public Mono<Void> insert(final M outputMessage, final Context context) {
        return delegate.insert(outputMessage, context);
    }
}
