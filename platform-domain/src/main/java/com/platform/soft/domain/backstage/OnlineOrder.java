/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: platform
 * $Id:  OnlineOrder.java 2016-08-10 21:01:21 $
 */






package com.platform.soft.domain.backstage;

import com.platform.soft.common.domain.BaseDomain;
import com.platform.soft.common.domain.CreateBaseDomain;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.*;

public class OnlineOrder extends CreateBaseDomain<Long> {
    private String mfrom;
    private String mto;
    private String linkMan;
    private String phoneNum;
    private String remark;
    private String content;
    private Integer isLink;
    private String linkResult;
    private String customer;

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public OnlineOrder(){
	}
    public void setMfrom(String value) {
        this.mfrom = value;
    }

    public String getMfrom() {
        return this.mfrom;
    }
    public void setMto(String value) {
        this.mto = value;
    }

    public String getMto() {
        return this.mto;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public void setPhoneNum(String value) {
        this.phoneNum = value;
    }

    public String getPhoneNum() {
        return this.phoneNum;
    }
    public void setRemark(String value) {
        this.remark = value;
    }

    public String getRemark() {
        return this.remark;
    }
    public void setContent(String value) {
        this.content = value;
    }

    public String getContent() {
        return this.content;
    }
    public void setIsLink(Integer value) {
        this.isLink = value;
    }

    public Integer getIsLink() {
        return this.isLink;
    }
    public void setLinkResult(String value) {
        this.linkResult = value;
    }

    public String getLinkResult() {
        return this.linkResult;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Mfrom",getMfrom())
			.append("Mto",getMto())
			.append("LinKman",getLinkMan())
			.append("PhoneNum",getPhoneNum())
			.append("Remark",getRemark())
			.append("Content",getContent())
			.append("IsLink",getIsLink())
			.append("LinkResult",getLinkResult())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof OnlineOrder == false) return false;
		if(this == obj) return true;
		OnlineOrder other = (OnlineOrder)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

