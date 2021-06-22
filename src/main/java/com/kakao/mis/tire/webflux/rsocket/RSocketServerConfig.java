package com.kakao.mis.tire.webflux.rsocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.cbor.Jackson2CborDecoder;
import org.springframework.http.codec.cbor.Jackson2CborEncoder;
import org.springframework.messaging.rsocket.RSocketStrategies;
import org.springframework.messaging.rsocket.annotation.support.RSocketMessageHandler;
import org.springframework.web.util.pattern.PathPatternRouteMatcher;

@Configuration
public class RSocketServerConfig {

    @Bean
    public RSocketMessageHandler rsocketMessageHandler() {
        final RSocketMessageHandler handler = new RSocketMessageHandler();
        handler.setRSocketStrategies(rsocketStrategies());
        return handler;
    }

    @Bean
    public RSocketStrategies rsocketStrategies() {
        return RSocketStrategies.builder()
                                .encoders(encoders -> encoders.add(new Jackson2CborEncoder()))
                                .decoders(decoders -> decoders.add(new Jackson2CborDecoder()))
                                .routeMatcher(new PathPatternRouteMatcher())
                                .build();
    }
}
