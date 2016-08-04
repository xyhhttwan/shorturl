package com.platform.soft.api;

import com.platform.soft.dao.IBaseDAO;
import com.platform.soft.domain.BaseDomain;
import com.platform.soft.service.IBaseService;
import com.platform.soft.service.IPageService;

/**
 * Created by baixiaobin on 16/8/4.
 */
public interface IUserService<D extends IBaseDAO<T>, T extends BaseDomain> extends IBaseService<D, T>, IPageService<D, T>{

}
