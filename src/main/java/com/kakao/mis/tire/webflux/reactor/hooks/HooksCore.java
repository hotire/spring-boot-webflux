package com.kakao.mis.tire.webflux.reactor.hooks;

import org.reactivestreams.Publisher;

import com.kakao.mis.tire.webflux.reactor.AssemblySnapshotCore;

import reactor.core.publisher.Hooks;

/**
 * @see Hooks
 */
public class HooksCore {

    static boolean GLOBAL_TRACE = initStaticGlobalTrace();

    static boolean initStaticGlobalTrace() {
        return Boolean.parseBoolean(System.getProperty("reactor.trace.operatorStacktrace",
                                                       "false"));
    }

    /**
     * @see Hooks#onOperatorDebug()
     */
    void onOperatorDebug() {
        Hooks.onOperatorDebug();
    }

    /**
     * @see Hooks#addAssemblyInfo(Publisher, reactor.core.publisher.FluxOnAssembly.AssemblySnapshot)
     */
    static <T, P extends Publisher<T>> Publisher<T> addAssemblyInfo(final P publisher, final AssemblySnapshotCore stacktrace) {
        return null;
    }
}
