package com.platform.soft.common.domain;

import java.io.Serializable;
import java.math.BigInteger;


/**
 * Created by baixiaobin on 16/8/4.
 */
public class BaseDomain<T> implements Serializable {

    protected static final long serialVersionUID = 6667600871541308622L;
    private T id;

    public BaseDomain() {
    }

    public T getId() {
        return this.id instanceof BigInteger? (T)Long.valueOf(String.valueOf(this.id)) :this.id;
    }

    public void setId(T id) {
        this.id = id;
    }
}
