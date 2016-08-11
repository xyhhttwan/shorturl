/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: platform
 * $Id:  DynamicOrder.java 2016-08-10 21:01:21 $
 */






package com.platform.soft.domain.backstage;

import com.platform.soft.common.domain.BaseDomain;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

public class DynamicOrder extends BaseDomain<Long> {
    private String orderId;
    private String arrivePosition;
    private Date arriveTime;

	public DynamicOrder(){
	}
    public void setOrderId(String value) {
        this.orderId = value;
    }

    public String getOrderId() {
        return this.orderId;
    }
    public void setArrivePosition(String value) {
        this.arrivePosition = value;
    }

    public String getArrivePosition() {
        return this.arrivePosition;
    }

    public void setArriveTime(Date value) {
        this.arriveTime = value;
    }

    public Date getArriveTime() {
        return this.arriveTime;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("OrderId",getOrderId())
			.append("ArrivePosition",getArrivePosition())
			.append("ArriveTime",getArriveTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof DynamicOrder == false) return false;
		if(this == obj) return true;
		DynamicOrder other = (DynamicOrder)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

