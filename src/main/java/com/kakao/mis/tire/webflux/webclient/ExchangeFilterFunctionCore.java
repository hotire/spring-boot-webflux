package com.kakao.mis.tire.webflux.webclient;

import java.util.function.Function;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.http.client.reactive.ClientHttpRequestDecorator;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;

import reactor.core.publisher.Mono;

public class ExchangeFilterFunctionCore {

    /**
     * @see ExchangeFilterFunction#ofRequestProcessor(Function)
     */
    static ExchangeFilterFunction ofRequestProcessor(final Function<ClientRequest, Mono<ClientRequest>> processor) {
        return ExchangeFilterFunction.ofRequestProcessor(clientResponse -> {
            return Mono.just(ClientRequest.from(clientResponse).body(new BodyInserter<>() {
                @Override
                public Mono<Void> insert(final ClientHttpRequest outputMessage, final Context context) {
                    final DataBuffer requestBodyBuffer = outputMessage.bufferFactory().allocateBuffer();
                    final ClientHttpRequest request = new ClientHttpRequestDecorator(outputMessage) {

                    };

                    return clientResponse.body()
                                         .insert(request, context);
                }
            }).build());
        });
    }

    /**
     * @see ExchangeFilterFunction#ofResponseProcessor(Function)
     */
    static ExchangeFilterFunction ofResponseProcessor(final Function<ClientResponse, Mono<ClientResponse>> processor) {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            clientResponse.statusCode();
            return Mono.just(clientResponse);
        });
    }
}
