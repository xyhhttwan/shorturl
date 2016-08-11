/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: platform
 * $Id:  TextMsg.java 2016-08-10 21:32:36 $
 */






package com.platform.soft.domain.backstage;

import com.platform.soft.common.domain.BaseDomain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.*;

public class TextMsg extends BaseDomain<Long> {
    private Long msgId;
    private String msgType;
    private String toUserName;
    private String fromUserName;
    private String content;
    private Long createTime;

	public TextMsg(){
	}
    public void setMsgId(Long value) {
        this.msgId = value;
    }

    public Long getMsgId() {
        return this.msgId;
    }
    public void setMsgType(String value) {
        this.msgType = value;
    }

    public String getMsgType() {
        return this.msgType;
    }
    public void setToUserName(String value) {
        this.toUserName = value;
    }

    public String getToUserName() {
        return this.toUserName;
    }
    public void setFromUserName(String value) {
        this.fromUserName = value;
    }

    public String getFromUserName() {
        return this.fromUserName;
    }
    public void setContent(String value) {
        this.content = value;
    }

    public String getContent() {
        return this.content;
    }
    public void setCreateTime(Long value) {
        this.createTime = value;
    }

    public Long getCreateTime() {
        return this.createTime;
    }

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("MsgId",getMsgId())
			.append("MsgType",getMsgType())
			.append("ToUserName",getToUserName())
			.append("FromUserName",getFromUserName())
			.append("Content",getContent())
			.append("CreateTime",getCreateTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TextMsg == false) return false;
		if(this == obj) return true;
		TextMsg other = (TextMsg)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

