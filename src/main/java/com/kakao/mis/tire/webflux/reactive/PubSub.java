package com.kakao.mis.tire.webflux.reactive;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 *  기존 Observable, Observer의 문제점, 한계
 *
 *  1. Complete
 *   : 특정 값을 사용해서 알려야 했다.
 *
 *  2. Error
 *   : 예외가 발생했을 경우, 처리하기 어렵다.
 *
 *
 *   Publisher - Observable
 *   Subscriber - Observer
 *
 */
@SuppressWarnings("unchecked")
public class PubSub {
    public static void main(String args[]) throws InterruptedException {

        ExecutorService es = Executors.newSingleThreadExecutor();

        Publisher p = new Publisher<>() {
            private Queue<Integer> queue = IntStream.range(1, 10).boxed()
                                                    .collect(Collectors.toCollection(LinkedList::new));

            /**
             * Subscription는
             * Publisher와 Subscriber의 중계역할
             *
             */
            @Override
            public void subscribe(Subscriber<? super Object> subscriber) {
                subscriber.onSubscribe(new Subscription() {

                    @Override
                    public void request(long n) {
                        es.execute(() -> {
                            int i = 0;
                            try {
                                while (i++ < n) {
                                    if (queue.isEmpty()) {
                                        subscriber.onComplete();
                                        break;
                                    } else {
                                        subscriber.onNext(queue.poll());
                                    }
                                }
                            } catch (RuntimeException e) {
                                subscriber.onError(e);
                            }
                        });
                    }

                    @Override
                    public void cancel() {
                    }
                });
            }
        };

        Subscriber s = new Subscriber() {
            private Subscription subscription;

            @Override
            public void onSubscribe(Subscription subscription) {
                System.out.println("on Subscribe");
                this.subscription = subscription;
                this.subscription.request(1);
            }

            @Override
            public void onNext(Object item) {
                System.out.println("on Next" + "," + item);
                this.subscription.request(1);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("on Error : " + throwable.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("on Complete ");
            }
        };

        p.subscribe(s);
        es.awaitTermination(10, TimeUnit.SECONDS);
        es.shutdown();
    }
}
