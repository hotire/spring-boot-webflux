package com.kakao.mis.tire.webflux.reactor.flux;

import java.util.function.Supplier;

import org.reactivestreams.Publisher;

import lombok.RequiredArgsConstructor;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;

/**
 * @see reactor.core.publisher.FluxOnAssembly
 */
public class FluxOnAssemblyCore<T> {

    /**
     * @see reactor.core.publisher.FluxOnAssembly#subscribeOrReturn(CoreSubscriber)
     */
    public CoreSubscriber<? super T> subscribeOrReturn(final CoreSubscriber<? super T> actual) {
        return null;
    }

    /**
     * @see reactor.core.publisher.FluxOnAssembly#wrapSubscriber(CoreSubscriber, Flux, Publisher, reactor.core.publisher.FluxOnAssembly.AssemblySnapshot)
     */
    static <T> CoreSubscriber<? super T> wrapSubscriber(final CoreSubscriber<? super T> actual,
                                                        final Flux<? extends T> source,
                                                        final Publisher<?> current,
                                                        final AssemblySnapshotCore snapshotStack) {
        return null;
    }


    @RequiredArgsConstructor
    static class AssemblySnapshotCore {
        final boolean isCheckpoint;
        final String description;
        final Supplier<String> assemblyInformationSupplier;
        String cached;
    }
}
