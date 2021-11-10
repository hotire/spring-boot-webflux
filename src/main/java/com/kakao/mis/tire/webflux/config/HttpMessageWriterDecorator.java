package com.kakao.mis.tire.webflux.config;

import org.reactivestreams.Publisher;
import org.springframework.core.ResolvableType;
import org.springframework.http.MediaType;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.http.codec.HttpMessageWriter;

import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

public class HttpMessageWriterDecorator<T> implements HttpMessageWriter<T> {

    private final HttpMessageWriter<T> delegate;

    public HttpMessageWriterDecorator(final HttpMessageWriter<T> delegate) {
        this.delegate = delegate;
    }

    protected HttpMessageWriter<T> getDelegate() {
        return delegate;
    }

    @Override
    public List<MediaType> getWritableMediaTypes() {
        return getDelegate().getWritableMediaTypes();
    }

    @Override
    public boolean canWrite(final ResolvableType resolvableType, final MediaType mediaType) {
        return getDelegate().canWrite(resolvableType, mediaType);
    }

    @Override
    public Mono<Void> write(final Publisher<? extends T> publisher, final ResolvableType resolvableType, final MediaType mediaType, final ReactiveHttpOutputMessage reactiveHttpOutputMessage, final Map<String, Object> hints) {
        return getDelegate().write(publisher, resolvableType, mediaType, reactiveHttpOutputMessage, hints);
    }
}
