package com.platform.soft.service.service;

import com.platform.soft.api.backstage.IUserService;
import com.platform.soft.common.dao.IBaseDAO;
import com.platform.soft.common.service.AbstractPageService;
import com.platform.soft.domain.backstage.User;
import com.platform.soft.service.dao.IUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by baixiaobin on 16/8/4.
 */
@Service
public class UserServiceImpl extends AbstractPageService<IBaseDAO<User>, User> implements IUserService<IBaseDAO<User>,User> {

    @Autowired
    private IUserDAO userDAO;


    @Override
    public IBaseDAO<User> getDao() {
        return userDAO;
    }
}
