package com.kakao.mis.tire.webflux;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

@Slf4j
@Order(-2)
@Component
public class CustomWebExceptionHandler implements WebExceptionHandler {

  @Override
  public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {

    if (ex instanceof IllegalStateException) {
      log.error("IllegalStateException : {}", ex.getMessage());
      exchange.getResponse().setStatusCode(HttpStatus.OK);
      return exchange.getResponse().setComplete();
    }

    Mono.just(1).subscribe();
    return Mono.error(ex);
  }
}
