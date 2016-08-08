package com.platform.soft.service.dao.backstage.ex;



import com.platform.soft.domain.backstage.ex.Permission;
import com.platform.soft.domain.backstage.ex.PermissionHelper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 权限dao层
 *
 * @author baixb
 * @version [v1.0，2015/6/18]
 */
public interface IPermissionDAO {

    /**
     * 新增权限信息
     *
     * @param permission
     * @return int 受影响的行数
     * @throws SQLException
     */
    int addPermission(Permission permission) throws SQLException;

    /**
     * 修改权限信息
     *
     * @param permission
     * @return int 受影响的行数
     * @throws SQLException
     */
    int updatePermission(Permission permission) throws SQLException;

    /**
     * 删除权限信息
     *
     * @param id
     * @return int 受影响的行数
     * @throws SQLException
     */
    int delPermissionById(@Param("id")int id) throws SQLException;

    /**
     * 查询权限信息分页
     *
     * @param map
     * @return List
     */
    List<Permission> getPermissionPageList(Map<String, Object> map);

    /**
     * 根据id查询权限信息
     *
     * @param id
     * @return Permission
     */
    Permission getPermissionById(@Param("id")int id);

    /**
     * 下拉框用到 只查询id和name
     *
     * @return list
     */
    List<PermissionHelper> getPermissionAll();


    /**
     * 判断是否有子类
     *
     * @param id
     * @return boolean
     */
    List<?> ifHasChildren(@Param("id")int id);

    /**
     * 根据用户id查找该用户的权限信息
     *
     * @param userId
     * @return List
     */
    List<Permission> getPermissionByUserId(@Param("userId")int userId);

    /**
     * 根据编码查询权限
     *
     * @param code
     * @return
     */
    Permission getPermissionByCode(@Param("code")String code);
}
