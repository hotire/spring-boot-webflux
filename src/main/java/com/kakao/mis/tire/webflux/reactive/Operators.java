package com.kakao.mis.tire.webflux.reactive;

import static java.util.stream.Collectors.toList;

import java.util.function.Function;
import java.util.stream.IntStream;

import lombok.extern.slf4j.Slf4j;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

@SuppressWarnings("SubscriberImplementation")
@Slf4j
public class Operators {
    public static void main(String... args) {
        Publisher<Integer> pub = fromIterable(IntStream.range(1, 10).boxed().collect(toList()));
        pub.subscribe(logSub());
        Publisher<Integer> mapPub = map(pub, integer -> integer * 10);
        Publisher<Integer> sumPub = sum(mapPub);
        sumPub.subscribe(logSub());
    }

    public static Publisher<Integer> sum(Publisher<Integer> pub) {
        return sub -> pub.subscribe(new DelegateSub<Integer, Integer>(sub) {
            private Integer sum = 0;

            @Override
            public void onNext(Integer item) {
                log.info("on Next : {} ", item);
                sum += item;
            }

            @Override
            public void onComplete() {
                sub.onNext(sum);
                sub.onComplete();
            }
        });
    }

    public static <T, R> Publisher<R> map2(Publisher<T> pub, Function<T, R> function) {
        return sub -> pub.subscribe(new DelegateSub<T, R>(sub) {
            @Override
            public void onNext(T t) {
                sub.onNext(function.apply(t));
            }
        });
    }

    public static <T, R> Publisher<R> map(Publisher<T> pub, Function<T, R> function) {
        return subscriber -> pub.subscribe(new Subscriber<>() {
            @Override
            public void onSubscribe(Subscription subscription) {
                subscriber.onSubscribe(subscription);
            }

            @Override
            public void onNext(T t) {
                subscriber.onNext(function.apply(t));
            }

            @Override
            public void onError(Throwable throwable) {
                subscriber.onError(throwable);
            }

            @Override
            public void onComplete() {
                subscriber.onComplete();
            }
        });
    }

    public static <T> Subscriber<? super T> logSub() {
        return new Subscriber<>() {
            private Subscription subscription;

            @Override
            public void onSubscribe(Subscription subscription) {
                log.debug("on Subscribe");
                this.subscription = subscription;
                this.subscription.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(T item) {
                log.debug("on Next : {}", item);
            }

            @Override
            public void onError(Throwable throwable) {
                log.debug("on Error : {}", throwable.getMessage());
            }

            @Override
            public void onComplete() {
                log.debug("on Complete");
            }
        };
    }

    public static <T> Publisher<T> fromIterable(Iterable<T> iterable) {
        return sub -> sub.onSubscribe(new Subscription() {
            @Override
            public void request(long n) {
                try {
                    log.debug("on Request");
                    iterable.forEach(sub::onNext);
                    sub.onComplete();
                } catch (RuntimeException e) {
                    sub.onError(e);
                }
            }

            @Override
            public void cancel() {
            }
        });
    }

}
