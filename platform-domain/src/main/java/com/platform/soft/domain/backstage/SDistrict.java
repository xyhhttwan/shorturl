/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: platform
 * $Id:  SDistrict.java 2016-08-10 21:01:21 $
 */






package com.platform.soft.domain.backstage;

import com.platform.soft.common.domain.BaseDomain;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.*;

public class SDistrict extends BaseDomain<Long> {
    private Long districtId;
    private String districtName;
    private Long cityId;
    private Date dateCreated;
    private Date dateUpdated;

	public SDistrict(){
	}
    public void setDistrictId(Long value) {
        this.districtId = value;
    }

    public Long getDistrictId() {
        return this.districtId;
    }
    public void setDistrictName(String value) {
        this.districtName = value;
    }

    public String getDistrictName() {
        return this.districtName;
    }
    public void setCityId(Long value) {
        this.cityId = value;
    }

    public Long getCityId() {
        return this.cityId;
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
			.append("DistrictId",getDistrictId())
			.append("DistrictName",getDistrictName())
			.append("CityId",getCityId())
			.append("DateCreated",getDateCreated())
			.append("DateUpdated",getDateUpdated())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getDistrictId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SDistrict == false) return false;
		if(this == obj) return true;
		SDistrict other = (SDistrict)obj;
		return new EqualsBuilder()
			.append(getDistrictId(),other.getDistrictId())
			.isEquals();
	}
}

