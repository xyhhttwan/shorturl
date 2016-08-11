/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: platform
 * $Id:  SProvince.java 2016-08-10 21:01:21 $
 */






package com.platform.soft.domain.backstage;

import com.platform.soft.common.domain.BaseDomain;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.*;

public class SProvince extends BaseDomain<Long> {
    private Long provinceId;
    private String provinceName;
    private Date dateCreated;
    private Date dateUpdated;

	public SProvince(){
	}
    public void setProvinceId(Long value) {
        this.provinceId = value;
    }

    public Long getProvinceId() {
        return this.provinceId;
    }
    public void setProvinceName(String value) {
        this.provinceName = value;
    }

    public String getProvinceName() {
        return this.provinceName;
    }

    public void setDateCreated(Date value) {
        this.dateCreated = value;
    }

    public Date getDateCreated() {
        return this.dateCreated;
    }

    public void setDateUpdated(Date value) {
        this.dateUpdated = value;
    }

    public Date getDateUpdated() {
        return this.dateUpdated;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ProvinceId",getProvinceId())
			.append("ProvinceName",getProvinceName())
			.append("DateCreated",getDateCreated())
			.append("DateUpdated",getDateUpdated())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getProvinceId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SProvince == false) return false;
		if(this == obj) return true;
		SProvince other = (SProvince)obj;
		return new EqualsBuilder()
			.append(getProvinceId(),other.getProvinceId())
			.isEquals();
	}
}

