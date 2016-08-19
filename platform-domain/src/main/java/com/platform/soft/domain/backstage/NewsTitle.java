/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: platform
 * $Id:  NewsTitle.java 2016-08-17 19:45:54 $
 */






package com.platform.soft.domain.backstage;

import com.platform.soft.common.domain.BaseDomain;
import com.platform.soft.common.domain.CreateBaseDomain;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.*;

public class NewsTitle extends CreateBaseDomain<Long> {
    private String title;
    private String content;
    private String pic;
    private Date postTime;
    private Date updateTime;

	public NewsTitle(){
	}
    public void setTitle(String value) {
        this.title = value;
    }

    public String getTitle() {
        return this.title;
    }
    public void setContent(String value) {
        this.content = value;
    }

    public String getContent() {
        return this.content;
    }
    public void setPic(String value) {
        this.pic = value;
    }

    public String getPic() {
        return this.pic;
    }

    public void setPostTime(Date value) {
        this.postTime = value;
    }

    public Date getPostTime() {
        return this.postTime;
    }

    public void setUpdateTime(Date value) {
        this.updateTime = value;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Title",getTitle())
			.append("Content",getContent())
			.append("Pic",getPic())
			.append("PostTime",getPostTime())
			.append("UpdateTime",getUpdateTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof NewsTitle == false) return false;
		if(this == obj) return true;
		NewsTitle other = (NewsTitle)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

