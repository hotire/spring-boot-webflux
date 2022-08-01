package com.kakao.mis.tire.webflux.body;

import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserter.Context;
import org.springframework.web.reactive.function.BodyInserters;

import reactor.core.publisher.Mono;

/**
 * @see BodyInserter
 */
public class BodyInserterCore {

    /**
     * @see BodyInserter#insert(ReactiveHttpOutputMessage, Context)
     * @see BodyInserters.DefaultFormInserter#insert(ClientHttpRequest, Context)
     */
    public <M> Mono<Void> insert(final M outputMessage, final Context context) {
        return Mono.empty();
    }

}
