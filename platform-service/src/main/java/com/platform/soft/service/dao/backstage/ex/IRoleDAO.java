package com.platform.soft.service.dao.backstage.ex;


import com.platform.soft.domain.backstage.ex.Role;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 角色dao层
 *
 * @author baixb
 * @version [v1.0，2015/6/17]
 */
public interface IRoleDAO {

    /**
     * 查询角色列表
     *
     * @param map
     * @return List
     */
    List<Role> getRolePageList(Map<String, Object> map);


    /**
     * 新增角色
     *
     * @param role
     * @return int 受影响的行数
     * @throws SQLException
     */
    int addRole(Role role) throws SQLException;


    /**
     * 删除角色
     *
     * @param id
     * @return int 受影响的行数
     * @throws SQLException
     */
    int delRole(@Param("id")int id) throws SQLException;

    /**
     * 更新角色
     *
     * @param role
     * @return int 受影响的行数
     * @throws SQLException
     */
    int updateRole(Role role) throws SQLException;

    /**
     * 根据id查询角色信息
     *
     * @param id
     * @return Role
     */
    Role getRoleById(@Param("id")int id);

    /**
     * 根据角色i的查询菜单信息
     *
     * @param roleId
     * @return List
     */
    List<Map<String, Object>> getMenuByRoleId(@Param("roleId")int roleId);

    /**
     * 删除角色菜单
     *
     * @param roleId
     * @return int 受影响的行数
     * @throws SQLException
     */
    int delRoleMenuByRoleId(@Param("roleId")int roleId) throws SQLException;

    /**
     * 新增角色菜单
     *
     * @param map
     * @return int 受影响的行数
     * @throws SQLException
     */
    int addRoleMenu(Map<String,Object> map) throws SQLException;

    /**
     * 根据角色id查询权限信息
     *
     * @param roleId
     * @return List
     */
    List<Map<String, Object>> getPermissionByRoleId(@Param("roleId")int roleId);

    /**
     * 新增角色权限信息
     *
     * @param map
     * @return int 影响的行数
     * @throws SQLException
     */
    int addRolePermission(Map<String,Object> map) throws SQLException;

    /**
     * 根据角色id删除权限信息
     *
     * @param roleId
     * @return int 受影响的行数
     * @throws SQLException
     */
    int delRolePermissionByRoleId(@Param("roleId")int roleId) throws SQLException;

    int queryRoleNameExist(Role role);

    /**
     * 查询全部的角色信息
     * @return  List
     */
    List<Role> getAllRoles();
}
