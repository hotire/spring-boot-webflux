package com.kakao.mis.tire.webflux.reactor;

import java.util.List;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
class MonoZipCoreTest {

    @Test
    void testVoid() throws InterruptedException {
        final List<String> list = List.of("1,2,3");

        final Mono<Void> mono = Mono.just(list)
                                    .map(it -> saveAll(it, 3000L))
                                    .then()
                                    .log();

        final Mono<Void> mono2 = Mono.just(list)
                                     .map(it -> saveAll(it, 4000L))
                                     .then()
                                     .log();

        Mono.zip(mono, mono2).block();
    }

    @Test
    void testZipDelayError() {
        final List<String> list = List.of("1,2,3");

        final Mono<Void> mono = Mono.just(list)
                                    .map(it -> saveAll(it, 3000L))
                                    .then()
                                    .log();

        final Mono<Void> mono2 = Mono.just(list)
                                     .map(it -> saveAll(it, 4000L))
                                     .then()
                                     .log();

        Mono.zipDelayError(mono, mono2).block();
    }

    @Test
    void testZipDelayErrorThrowError() {
        final List<String> list = List.of("1,2,3");

        final Mono<Void> mono = Mono.create(sink -> { throw new RuntimeException("exception1"); })
                                    .then()
                                    .log();

        final Mono<List<String>> mono2 = Mono.just(list)
                                             .map(it -> saveAll(it, 4000L))
                                             .log();

        Mono.zipDelayError(mono2, mono).block();
    }

    @Test
    void test() throws InterruptedException {
        final List<String> list = List.of("1,2,3");

        final Mono<List<String>> mono = Mono.just(list)
                                            .map(it -> saveAll(it, 3000L))
                                            .log();

        final Mono<List<String>> mono2 = Mono.just(list)
                                             .map(it -> saveAll(it, 4000L))
                                             .log();

        Mono.zip(mono, mono2).subscribe();

        Thread.sleep(5000L);
    }

    List<String> saveAll(final List<String> list, final long millis) {
        try {
            Thread.sleep(millis);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
        log.info("save all");
        return list;
    }

}