package com.platform.soft.service.service.backstage.ex;

import com.platform.soft.api.backstage.ex.ISystemUserService;
import com.platform.soft.common.utils.encryptUtils.EncryptUtils;
import com.platform.soft.service.dao.backstage.ex.ISystemUserDAO;
import com.platform.soft.domain.backstage.ex.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 用户登录dao层
 *
 * @author baixb
 * @version [v1.0，2015/6/16]
 */
@Service
public class SystemUserServiceImpl implements ISystemUserService {

    @Autowired
    private ISystemUserDAO systemUserLoginDao;
    @Override
    public SystemUser login(String userName, String password) {

        return systemUserLoginDao.login(userName,password);
    }

    /**
     * 根据调价查询用户列表
     *
     * @param map
     * @return List<SystemUser>
     */
    @Override
    public List<SystemUser> getUserPageList(Map<String, Object> map) {
        return systemUserLoginDao.getUserPageList(map);
    }

    /**
     * 新增用户
     *
     * @param user
     * @return int 受影响的行数
     * @throws SQLException
     */
    @Override
    public int addSystemUser(SystemUser user) throws SQLException {
        user.setPassword( EncryptUtils.encodeMD5String(user.getPassword()));
        return systemUserLoginDao.addSystemUser(user);
    }

    /**
     * 更新用户信息
     *
     * @param user
     * @return int 受影响的行数
     * @throws SQLException
     */
    @Override
    public int updateUser(SystemUser user) throws SQLException {
        return systemUserLoginDao.updateUser(user);
    }

    /**
     * 根据用户id查询该用户
     *
     * @param id
     * @return SystemUser
     */
    @Override
    public SystemUser getUserById(int id) {
        return systemUserLoginDao.getUserById(id);
    }

    /**
     * 根据主键id删除该条信息
     *
     * @param ids
     * @return int 受影响的行数
     */
    @Override
    public int delUser(String ids) throws SQLException {

        String ids_[] = ids.split(",");
        int counts = 0;
        for(String id_:ids_){
            int id = Integer.parseInt(id_);
            delUserRole(id);
            counts+=systemUserLoginDao.delUser(id);
        }
        return counts ;
    }

    /**
     * 根据用户id跟新密码
     *
     * @param map
     * @return int 受影响的行数
     * @throws SQLException
     */
    @Override
    public int updateUserPwd(Map<String, Object> map) throws SQLException {
        map.put("password",EncryptUtils.encodeMD5String(map.get("password").toString()));
        return systemUserLoginDao.updateUserPwd(map);
    }

    /**
     * 根据用户id查询该用户的角色信息
     *
     * @param userId
     * @return List
     */
    @Override
    public List<Map<String, Object>> getRoleListByUserId(int userId) {
        return systemUserLoginDao.getRoleListByUserId(userId);
    }

    /**
     * 删除该用户的角色
     *
     * @param userId
     * @return int 受影响的行数
     * @throws SQLException
     */
    @Override
    @Transactional
    public int delUserRole(int userId) throws SQLException {
        return systemUserLoginDao.delUserRole(userId);
    }

    /**
     * 新增用户角色
     *
     * @param userId
     * @param roleId
     * @return int 返回收影响的行数
     * @throws SQLException
     */
    @Override
    public int addUserRole(int userId, int[] roleId) throws SQLException {
        delUserRole(userId);
        int counts=0;
        if(null!=roleId && roleId.length>0){
            if(roleId.length>0){
                for(int i:roleId){
                    int count = systemUserLoginDao.addUserRole(userId,i);
                    counts+=count;
                }
            }
        }

        return counts;
    }

    /**
     * 检查该用户名是否存在
     *
     * @param userName
     * @return true 存在 false 不存在
     */
    @Override
    public boolean checkUserName(String userName) {
        List<?> list = systemUserLoginDao.checkUserName(userName);
        if(null!=list && list.size()>0){
            return true;
        }
        return false;
    }
}
