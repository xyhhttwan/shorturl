package com.platform.soft.common.domain;

import java.util.Date;

/**
 * Created by baixiaobin on 16/8/4.
 */
public class CreateBaseDomain <T> extends BaseDomain<T> {
    private Object creator;
    private Long createDate;
    private Object lastModifier;
    private Long lastModDate = Long.valueOf(System.currentTimeMillis());
    private Integer status;
    private String creatorName;
    private String lastModifierName;

    public CreateBaseDomain() {
    }

    public Object getCreator() {
        return this.creator;
    }

    public void setCreator(Object creator) {
        this.creator = creator;
    }

    public Long getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public Object getLastModifier() {
        return this.lastModifier;
    }

    public void setLastModifier(Object lastModifier) {
        this.lastModifier = lastModifier;
    }

    public Long getLastModDate() {
        return this.lastModDate;
    }

    public void setLastModDate(Long lastModDate) {
        this.lastModDate = lastModDate;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateDateAsDate() {
        return this.createDate == null?new Date():(this.createDate.longValue() > 0L?new Date(this.createDate.longValue()):null);
    }

    public Date getLastModDateAsDate() {
        return this.lastModDate == null?new Date():(this.lastModDate.longValue() > 0L?new Date(this.lastModDate.longValue()):null);
    }

    public String getCreatorName() {
        return this.creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getLastModifierName() {
        return this.lastModifierName;
    }

    public void setLastModifierName(String lastModifierName) {
        this.lastModifierName = lastModifierName;
    }
}
