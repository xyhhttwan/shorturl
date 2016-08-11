/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: platform
 * $Id:  CommonService.java 2016-08-10 21:01:20 $
 */

package com.platform.soft.api.backstage;


import com.platform.soft.common.dao.IBaseDAO;
import com.platform.soft.common.domain.BaseDomain;
import com.platform.soft.common.service.IBaseService;
import com.platform.soft.common.service.IPageService;

public interface ICommonService<D extends IBaseDAO<T>, T extends BaseDomain> extends IBaseService<D, T>,IPageService<D, T> {

}
