package com.kakao.mis.tire.webflux.reactor.subscriber;

import reactor.core.CoreSubscriber;
import reactor.util.context.Context;

/**
 * @see CoreSubscriber
 */
public class CoreSubscriberCore {

    /**
     * @see CoreSubscriber#currentContext()
     */
    public Context currentContext(){
        return Context.empty();
    }
}
