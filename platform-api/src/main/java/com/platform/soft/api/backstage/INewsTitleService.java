/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: platform
 * $Id:  NewsTitleService.java 2016-08-17 19:45:54 $
 */

package com.platform.soft.api.backstage;


import com.platform.soft.common.dao.IBaseDAO;
import com.platform.soft.common.domain.BaseDomain;
import com.platform.soft.common.service.IBaseService;
import com.platform.soft.common.service.IPageService;

public interface INewsTitleService<D extends IBaseDAO<T>, T extends BaseDomain> extends IBaseService<D, T>,IPageService<D, T> {

}
