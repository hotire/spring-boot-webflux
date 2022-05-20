package com.kakao.mis.tire.webflux.reactor;

import java.util.stream.Stream;

import reactor.core.Scannable;
import reactor.core.Scannable.Attr;
import reactor.util.annotation.Nullable;
import reactor.util.function.Tuple2;

/**
 * @see reactor.core.Scannable
 *
 * A Scannable component exposes state in a non strictly memory consistent way and results should be understood as best-effort hint of the underlying state.  This is useful to retro-engineer a component graph such as a flux operator chain via 'Stream' queries from 'actuals', 'parents' and 'inners' This allows for isiting patterns and possibly enable serviceability features.
Scannable is also a useful tool for the advanced user eager to learn which kind of state we usually manage in the package-scope schedulers or operators implementations.
 */
public interface ScannableCore {

    static Scannable from(@Nullable final Object o) {
        return null;
    }

    default Stream<? extends Scannable> actuals() {
        return Stream.empty();
    }

    default Stream<? extends Scannable> inners() {
        return Stream.empty();
    }

    default boolean isScanAvailable(){
        return true;
    }

    default String name() {
        return null;
    }

    default String stepName() {
        return null;
    }

    default Stream<String> steps() {
        return Stream.empty();
    }

    default Stream<? extends Scannable> parents() {
        return Stream.empty();
    }


    Object scanUnsafe(Attr key);


    default <T> T scan(final Attr<T> key) {
        return null;
    }

    default <T> T scanOrDefault(final Attr<T> key, final T defaultValue) {
        return defaultValue;
    }

    default Stream<Tuple2<String, String>> tags() {
        return Stream.empty();
    }
}
