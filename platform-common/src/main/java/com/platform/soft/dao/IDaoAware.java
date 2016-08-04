package com.platform.soft.dao;

import com.platform.soft.domain.BaseDomain;

/**
 * Created by baixiaobin on 16/8/4.
 */
public interface IDaoAware <D extends IBaseDAO, T extends BaseDomain> {
    D getDao();
}