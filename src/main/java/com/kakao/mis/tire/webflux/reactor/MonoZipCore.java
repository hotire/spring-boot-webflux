package com.kakao.mis.tire.webflux.reactor;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import reactor.core.CoreSubscriber;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Operators;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

/**
 * @see reactor.core.publisher.MonoZip
 */
public class MonoZipCore {

    /**
     * @see reactor.core.publisher.Mono#zip(Mono, Mono);
     */
    public static <T1, T2> Mono<Tuple2<T1, T2>> zip(final Mono<? extends T1> p1, final Mono<? extends T2> p2) {
        return Mono.zip(p1, p2, tuple2Function());
    }

    static <A, B> BiFunction<A, B, Tuple2<A, B>> tuple2Function() {
        return TUPLE2_BIFUNCTION;
    }

    static final BiFunction TUPLE2_BIFUNCTION = Tuples::of;

    /**
     * @see  reactor.core.publisher.FluxZip.PairwiseZipper
     */
    static final class CustomPairwiseZipper<R> implements Function<Object[], R> {
        final BiFunction[] zippers;

        CustomPairwiseZipper(final BiFunction[] zippers) {
            this.zippers = zippers;
        }

        @Override
        public R apply(final Object[] args) {
            Object o = zippers[0].apply(args[0], args[1]);
            for (int i = 1; i < zippers.length; i++) {
                o = zippers[i].apply(o, args[i + 1]);
            }
            return (R) o;
        }

        public CustomPairwiseZipper then(final BiFunction zipper) {
            final BiFunction[] zippers = this.zippers;
            final int n = zippers.length;
            final BiFunction[] newZippers = new BiFunction[n + 1];
            System.arraycopy(zippers, 0, newZippers, 0, n);
            newZippers[n] = zipper;

            return new CustomPairwiseZipper(newZippers);
        }
    }

    static final class CustomZipCoordinator<R> extends Operators.MonoSubscriber<Object, R> {

        boolean delayError;

        final Subscriber<R>[] subscribers;

        final CoreSubscriber<? super R> actual;

        static final AtomicIntegerFieldUpdater<CustomZipCoordinator> DONE =
                AtomicIntegerFieldUpdater.newUpdater(CustomZipCoordinator.class, "done");

        public CustomZipCoordinator(final CoreSubscriber<? super R> actual, final Subscriber<R>[] subscribers) {
            super(actual);
            this.actual = actual;
            this.subscribers = subscribers;
        }

        void signal() {
        }

        void cancelExcept(final Subscriber<R> source) {
        }
    }

    static final class CustomZipInner<R> implements CoreSubscriber<R> {

        final CustomZipCoordinator<R> parent;

        private R value;

        CustomZipInner(final CustomZipCoordinator<R> parent) {
            this.parent = parent;
        }

        @Override
        public void onComplete() {
            if (value == null) {
                if (parent.delayError) {
                    parent.signal();
                } else {
                    final int n = parent.subscribers.length;
                    if (CustomZipCoordinator.DONE.getAndSet(parent, n) != n) {
                        parent.cancelExcept(this);
                        parent.actual.onComplete();
                    }
                }
            }
        }

        @Override
        public void onSubscribe(final Subscription s) {

        }

        @Override
        public void onNext(final R r) {

        }

        @Override
        public void onError(final Throwable t) {

        }
    }
}
