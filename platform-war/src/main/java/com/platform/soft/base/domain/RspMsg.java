package com.platform.soft.base.domain;

import java.io.Serializable;

/**
 * 返回对象
 *
 * @author baixb
 * @version [v1.0，2015/6/16]
 */
public class RspMsg implements Serializable {


    /**
     * 标志成功或者失败
     */
    protected String result = "true";

    /**
     * 返回码
     */
    protected String code;

    /**
     * 返回消息
     */
    protected String message;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
