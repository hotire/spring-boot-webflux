package com.kakao.mis.tire.webflux.subscriber.context;

import java.util.Map.Entry;
import java.util.stream.Stream;

import reactor.util.context.Context;

public class MyContext implements Context {

    @Override
    public <T> T get(final Object key) {
        return null;
    }

    @Override
    public boolean hasKey(final Object key) {
        return false;
    }

    @Override
    public Context put(final Object key, final Object value) {
        return null;
    }

    @Override
    public Context delete(final Object key) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Stream<Entry<Object, Object>> stream() {
        return null;
    }
}
