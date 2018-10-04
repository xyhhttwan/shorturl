package com.platform.soft.common.promise.concurrent;

/**
 * 异步任务
 *
 * @param <V>
 */
public interface AsyncTask<V> {
    V call() throws Exception;
}
