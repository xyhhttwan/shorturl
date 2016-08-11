/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: platform
 * $Id:  SCity.java 2016-08-10 21:01:21 $
 */






package com.platform.soft.domain.backstage;

import com.platform.soft.common.domain.BaseDomain;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.*;

public class SCity extends BaseDomain<Long> {
    private Long cityId;
    private String cityName;
    private String zipCode;
    private Long provinceId;
    private Date dateCreated;
    private Date dateUpdated;

	public SCity(){
	}
    public void setCityId(Long value) {
        this.cityId = value;
    }

    public Long getCityId() {
        return this.cityId;
    }
    public void setCityName(String value) {
        this.cityName = value;
    }

    public String getCityName() {
        return this.cityName;
    }
    public void setZipCode(String value) {
        this.zipCode = value;
    }

    public String getZipCode() {
        return this.zipCode;
    }
    public void setProvinceId(Long value) {
        this.provinceId = value;
    }

    public Long getProvinceId() {
        return this.provinceId;
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
			.append("CityId",getCityId())
			.append("CityName",getCityName())
			.append("ZipCode",getZipCode())
			.append("ProvinceId",getProvinceId())
			.append("DateCreated",getDateCreated())
			.append("DateUpdated",getDateUpdated())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCityId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SCity == false) return false;
		if(this == obj) return true;
		SCity other = (SCity)obj;
		return new EqualsBuilder()
			.append(getCityId(),other.getCityId())
			.isEquals();
	}
}

