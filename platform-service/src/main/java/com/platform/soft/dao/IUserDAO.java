package com.platform.soft.dao;

import com.platform.soft.domain.User;

/**
 * Created by baixiaobin on 16/8/3.
 */
public interface IUserDAO extends IBaseDAO<User> {

    int add();
}
