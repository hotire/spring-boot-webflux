package com.kakao.mis.tire.webflux.reactor.flux;

import java.time.Duration;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;


@DisplayName("no assert, development test")
@Slf4j
class FluxHotColdTest {
    private Stream<String> getMovies() {
        return Stream.of(
                "타짜",
                "범죄도시",
                "기생충",
                "설국열차",
                "실미도"
        );
    }

    @Test
    void cinemaCache() throws InterruptedException {
        final Flux<String> netFlux = Flux.fromStream(this::getMovies)
                                         .doOnSubscribe(s -> log.info("영화 스트리밍 시작"))
                                         .delayElements(Duration.ofSeconds(1))
                                         .cache();

        netFlux.publishOn(Schedulers.newBoundedElastic(1, 1, "철수"))
               .subscribe(scene -> log.info("철수가 보는 영화 : " + scene));
        Thread.sleep(2000L);
        netFlux.publishOn(Schedulers.newBoundedElastic(1, 1, "영희"))
               .subscribe(scene -> log.info("영희가 보는 영화 : " + scene));

        Thread.sleep(5000L);
    }

    @Test
    void cinemaOnlyOnce() throws InterruptedException {
        final Flux<String> netFlux = Flux.fromStream(this::getMovies)
                                         .doOnSubscribe(s -> log.info("영화 스트리밍 시작"))
                                         .delayElements(Duration.ofSeconds(1))
                                         .publish()
                                         .autoConnect();

        netFlux.publishOn(Schedulers.newBoundedElastic(1, 1, "철수"))
               .subscribe(scene -> log.info("철수가 보는 영화 : " + scene));
        Thread.sleep(6000L);
        netFlux.publishOn(Schedulers.newBoundedElastic(1, 1, "영희"))
               .subscribe(scene -> log.info("영희가 보는 영화 : " + scene));

        Thread.sleep(5000L);
    }

    @Test
    void cinema() throws InterruptedException {
        final Flux<String> netFlux = Flux.fromStream(this::getMovies)
                                         .doOnSubscribe(s -> log.info("영화 스트리밍 시작"))
                                         .delayElements(Duration.ofSeconds(1))
                                         .share();

        netFlux.publishOn(Schedulers.newBoundedElastic(1, 1, "철수"))
               .subscribe(scene -> log.info("철수가 보는 영화 : " + scene));
        Thread.sleep(6000L);
        netFlux.publishOn(Schedulers.newBoundedElastic(1, 1, "영희"))
               .subscribe(scene -> log.info("영희가 보는 영화 : " + scene));

        Thread.sleep(5000);
    }

    @Test
    void netflix() throws InterruptedException {
        final Flux<String> netFlux = Flux.fromStream(this::getMovies)
                                         .doOnSubscribe(s -> log.info("영화 스트리밍 시작"))
                                         .delayElements(Duration.ofSeconds(1));

        netFlux.publishOn(Schedulers.newBoundedElastic(1, 1, "철수"))
               .subscribe(scene -> log.info("철수가 보는 영화 : " + scene));
        Thread.sleep(1000L);
        netFlux.publishOn(Schedulers.newBoundedElastic(1, 1, "영희"))
               .subscribe(scene -> log.info("영희가 보는 영화 : " + scene));

        Thread.sleep(5000);
    }
}
