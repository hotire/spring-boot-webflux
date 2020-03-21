package com.kakao.mis.tire.webflux.config;

import org.springframework.boot.web.codec.CodecCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.CodecConfigurer;

@Configuration
public class CodecConfig implements CodecCustomizer {

    @Override
    public void customize(CodecConfigurer configurer) {
//        configurer.customCodecs().register(new HttpMessageWriter<>() {
//            @Override
//            public List<MediaType> getWritableMediaTypes() {
//                return null;
//            }
//            @Override
//            public boolean canWrite(ResolvableType elementType, MediaType mediaType) {
//                return false;
//            }
//            @Override
//            public Mono<Void> write(Publisher<?> inputStream, ResolvableType elementType, MediaType mediaType, ReactiveHttpOutputMessage message, Map<String, Object> hints) {
//                return null;
//            }
//        });
    }
}
