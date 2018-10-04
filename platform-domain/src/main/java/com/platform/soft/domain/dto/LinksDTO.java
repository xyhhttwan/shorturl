package com.platform.soft.domain.dto;

import java.io.Serializable;

public class LinksDTO implements Serializable {


    private String errMsg;

    private String longuUrl;

    private String shortUrl;

    private Boolean result;

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getLonguUrl() {
        return longuUrl;
    }

    public void setLonguUrl(String longuUrl) {
        this.longuUrl = longuUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }
}
