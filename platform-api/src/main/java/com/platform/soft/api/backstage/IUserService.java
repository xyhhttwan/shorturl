package com.platform.soft.api.backstage;

import com.platform.soft.common.dao.IBaseDAO;
import com.platform.soft.common.domain.BaseDomain;
import com.platform.soft.common.service.IBaseService;
import com.platform.soft.common.service.IPageService;


/**
 * Created by baixiaobin on 16/8/4.
 */
public interface IUserService <D extends IBaseDAO<T>, T extends BaseDomain> extends IBaseService<D, T>, IPageService<D, T> {
}
