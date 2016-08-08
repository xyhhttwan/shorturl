package com.platform.soft.service.dao.backstage.ex;


import com.platform.soft.domain.backstage.ex.SystemUser;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 用户dao层接口
 *
 * @author baixb
 * @version [v1.0，2015/6/16]
 */

public interface ISystemUserDAO {

    /**
     * 用户登录
     * @param userName
     * @param password
     * @return SystemUser
     */
    SystemUser login(@Param("userName") String userName, @Param("password") String password);

    /**
     * 根据调价查询用户列表
     * @param map
     * @return List<SystemUser>
     */
    List<SystemUser> getUserPageList(Map<String, Object> map);

    /**
     * 新增用户
     * @param user
     * @throws SQLException
     * @return  int 受影响的行数
     */
    int addSystemUser(SystemUser user)throws SQLException;

    /**
     * 更新用户信息
     * @param user
     * @return int 受影响的行数
     * @throws SQLException
     */
    int updateUser(SystemUser user)throws SQLException;

    /**
     * 根据用户id查询该用户
     * @param id
     * @return SystemUser
     */
    SystemUser getUserById(@Param("id")int id);

    /**
     * 根据主键id删除该条信息
     * @param id
     * @return int 受影响的行数
     */
    int delUser(@Param("id")int id)throws SQLException;

    /**
     * 根据用户id跟新密码
     * @param map
     * @return int 受影响的行数
     * @throws SQLException
     */
    int updateUserPwd(Map<String, Object> map)throws SQLException;

    /**
     * 根据用户id查询该用户的角色信息
     * @param userId
     * @return List
     */
    List<Map<String,Object>> getRoleListByUserId(@Param("userId")int userId);

    /**
     * 删除该用户的角色
     * @param userId
     * @return int 受影响的行数
     * @throws SQLException
     */
    int delUserRole(int userId)throws SQLException;

    /**
     * 新增用户角色
     * @param userId
     * @param roleId
     * @return int 返回收影响的行数
     * @throws SQLException
     */
    int addUserRole(@Param("userId")int userId, @Param("roleId")int roleId)throws  SQLException;

    /**
     * 检查该用户名是否存在
     * @param systemName
     * @return true 存在 false 不存在
     */
    List<Object> checkUserName(@Param("systemName")String systemName);


}
