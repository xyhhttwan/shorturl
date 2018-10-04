package com.platform.soft.common.promise.core;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


public class DefaultPromise<V> extends Promise<V> {

    public Future<V> future;

    private Throwable throwable = new Throwable("NONE");

    public DefaultPromise(Future future) {
        this.future = future;
    }



    @Override
    public V get() {
        try {
            return future.get();
        } catch (Exception e) {
            throwable = e;
        }
        return null;
    }

    @Override
    public V get(long timeout, TimeUnit unit) {
        try {
            return future.get(timeout, unit);
        } catch (Exception e) {
            throwable = e;
        }
        return null;
    }

    @Override
    public Throwable getException() {
        return throwable;
    }
}