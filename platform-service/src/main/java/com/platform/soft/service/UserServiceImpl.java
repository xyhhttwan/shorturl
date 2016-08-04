package com.platform.soft.service;

import com.platform.soft.api.IUserService;
import com.platform.soft.dao.IBaseDAO;
import com.platform.soft.dao.IUserDAO;
import com.platform.soft.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by baixiaobin on 16/8/4.
 */
@Service
public class UserServiceImpl extends  AbstractPageService<IBaseDAO<User>, User> implements IUserService<IBaseDAO<User>,User> {

    @Autowired
    private IUserDAO userDAO;


    @Override
    public IBaseDAO<User> getDao() {
        return userDAO;
    }
}
