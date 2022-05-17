package com.kakao.mis.tire.webflux.reactor.hooks;

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
}
