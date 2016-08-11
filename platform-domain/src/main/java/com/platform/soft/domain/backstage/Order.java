/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: platform
 * $Id:  Order.java 2016-08-10 21:01:21 $
 */






package com.platform.soft.domain.backstage;

import com.platform.soft.common.domain.BaseDomain;
import com.platform.soft.common.domain.CreateBaseDomain;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


import java.util.*;

public class Order extends CreateBaseDomain<Long> {
    private String orderId;
    private String mfrom;
    private String mto;
    private String content;
    private Double price;
    private String carrier;
    private String linkPhone;

	public Order(){
	}
    public void setOrderId(String value) {
        this.orderId = value;
    }

    public String getOrderId() {
        return this.orderId;
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
    public void setContent(String value) {
        this.content = value;
    }

    public String getContent() {
        return this.content;
    }
    public void setPrice(Double value) {
        this.price = value;
    }

    public Double getPrice() {
        return this.price;
    }
    public void setCarrier(String value) {
        this.carrier = value;
    }

    public String getCarrier() {
        return this.carrier;
    }
    public void setLinkPhone(String value) {
        this.linkPhone = value;
    }

    public String getLinkPhone() {
        return this.linkPhone;
    }

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("OrderId",getOrderId())
			.append("Mfrom",getMfrom())
			.append("Mto",getMto())
			.append("Content",getContent())
			.append("Price",getPrice())
			.append("Carrier",getCarrier())
			.append("LinkPhone",getLinkPhone())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Order == false) return false;
		if(this == obj) return true;
		Order other = (Order)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

