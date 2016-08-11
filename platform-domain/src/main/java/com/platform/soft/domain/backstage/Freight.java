/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: platform
 * $Id:  Freight.java 2016-08-10 21:01:21 $
 */






package com.platform.soft.domain.backstage;

import com.platform.soft.common.domain.BaseDomain;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.*;

public class Freight extends BaseDomain<Long> {
    private String from;
    private String to;
    private String des;
    private String price;

	public Freight(){
	}
    public void setFrom(String value) {
        this.from = value;
    }

    public String getFrom() {
        return this.from;
    }
    public void setTo(String value) {
        this.to = value;
    }

    public String getTo() {
        return this.to;
    }
    public void setDes(String value) {
        this.des = value;
    }

    public String getDes() {
        return this.des;
    }
    public void setPrice(String value) {
        this.price = value;
    }

    public String getPrice() {
        return this.price;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("From",getFrom())
			.append("To",getTo())
			.append("Des",getDes())
			.append("Price",getPrice())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Freight == false) return false;
		if(this == obj) return true;
		Freight other = (Freight)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

