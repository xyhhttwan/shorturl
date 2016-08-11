/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: platform
 * $Id:  Common.java 2016-08-10 21:01:20 $
 */






package com.platform.soft.domain.backstage;

import com.platform.soft.common.domain.BaseDomain;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.*;

public class Common extends BaseDomain<Long> {
    private String aboutUs;
    private String phoneNum;
    private String telPhone;
    private String complaintsNum;
    private String companyDes;
    private String address;

	public Common(){
	}
    public void setAboutUs(String value) {
        this.aboutUs = value;
    }

    public String getAboutUs() {
        return this.aboutUs;
    }
    public void setPhoneNum(String value) {
        this.phoneNum = value;
    }

    public String getPhoneNum() {
        return this.phoneNum;
    }
    public void setTelPhone(String value) {
        this.telPhone = value;
    }

    public String getTelPhone() {
        return this.telPhone;
    }
    public void setComplaintsNum(String value) {
        this.complaintsNum = value;
    }

    public String getComplaintsNum() {
        return this.complaintsNum;
    }
    public void setCompanyDes(String value) {
        this.companyDes = value;
    }

    public String getCompanyDes() {
        return this.companyDes;
    }
    public void setAddress(String value) {
        this.address = value;
    }

    public String getAddress() {
        return this.address;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("AboutUs",getAboutUs())
			.append("PhoneNum",getPhoneNum())
			.append("TelPhone",getTelPhone())
			.append("ComplaintsNum",getComplaintsNum())
			.append("CompanyDes",getCompanyDes())
			.append("Address",getAddress())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Common == false) return false;
		if(this == obj) return true;
		Common other = (Common)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

