/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: platform
 * $Id:  News.java 2016-08-17 19:45:54 $
 */






package com.platform.soft.domain.backstage;

import com.platform.soft.common.domain.CreateBaseDomain;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.*;

public class News extends CreateBaseDomain<Long> {
	private String title;
	private String content;
	private Integer newsType;
	private String pic;
	private java.util.Date postTime;
	private java.util.Date updateTime;

	public News(){
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
	public void setNewsType(Integer value) {
		this.newsType = value;
	}

	public Integer getNewsType() {
		return this.newsType;
	}
	public void setPic(String value) {
		this.pic = value;
	}

	public String getPic() {
		return this.pic;
	}

	public void setPostTime(java.util.Date value) {
		this.postTime = value;
	}

	public java.util.Date getPostTime() {
		return this.postTime;
	}

	public void setUpdateTime(java.util.Date value) {
		this.updateTime = value;
	}

	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
				.append("Id",getId())
				.append("Title",getTitle())
				.append("Content",getContent())
				.append("CreateDate",getCreateDate())
				.append("Creator",getCreator())
				.append("LastModifier",getLastModifier())
				.append("LastModDate",getLastModDate())
				.append("Status",getStatus())
				.append("NewsType",getNewsType())
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
		if(obj instanceof News == false) return false;
		if(this == obj) return true;
		News other = (News)obj;
		return new EqualsBuilder()
				.append(getId(),other.getId())
				.isEquals();
	}
}

