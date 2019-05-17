package com.kakao.mis.tire.webflux.filter;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.resolve;

import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.server.HandlerFilterFunction;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoSink;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class ExampleHandlerFilterFunction
  implements HandlerFilterFunction<ServerResponse, ServerResponse> {

  @Override
  public Mono<ServerResponse> filter(ServerRequest serverRequest,
    HandlerFunction<ServerResponse> handlerFunction) {
    if (serverRequest.pathVariable("name").equalsIgnoreCase("test")) {
      return ServerResponse.status(FORBIDDEN).build();
    }
    return handlerFunction.handle(serverRequest);
  }
}
