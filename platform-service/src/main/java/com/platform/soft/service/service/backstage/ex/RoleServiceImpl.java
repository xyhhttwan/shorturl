package com.platform.soft.service.service.backstage.ex;

import cn.jpush.api.utils.StringUtils;
import com.platform.soft.api.backstage.ex.IRoleService;
import com.platform.soft.service.dao.backstage.ex.IRoleDAO;
import com.platform.soft.domain.backstage.ex.Role;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 角色service实现类
 *
 * @author baixb
 * @version [v1.0，2015/6/18]
 */
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDAO roleDao;

    /**
     * 查询角色列表
     *
     * @param map
     * @return List
     */
    @Override
    public List<Role> getRolePageList(Map<String, Object> map) {
        return roleDao.getRolePageList(map);
    }

    /**
     * 新增角色
     *
     * @param role
     * @return int 受影响的行数
     * @throws SQLException
     */
    @Override
    public int addRole(Role role) throws SQLException {
        return roleDao.addRole(role);
    }

    /**
     * 删除角色
     *
     * @param ids
     * @return int 受影响的行数
     * @throws SQLException
     */
    @Override
    public int delRole(String ids) throws SQLException {
        if (StringUtils.isNotEmpty(ids)) {
            String[] idList = ids.split(",");
            for (String id : idList) {
                roleDao.delRole(Integer.valueOf(id));
            }
        }
        return 0;
    }

    /**
     * 更新角色
     *
     * @param role
     * @return int 受影响的行数
     * @throws SQLException
     */
    @Override
    public int updateRole(Role role) throws SQLException {
        return roleDao.updateRole(role);
    }

    /**
     * 根据id查询角色信息
     *
     * @param id
     * @return Role
     */
    @Override
    public Role getRoleById(int id) {
        return roleDao.getRoleById(id);
    }

    /**
     * 根据角色i的查询菜单信息
     *
     * @param roleId
     * @return List
     */
    @Override
    public List<Map<String, Object>> getMenuByRoleId(int roleId) {
        return roleDao.getMenuByRoleId(roleId);
    }

    /**
     * 删除角色菜单
     *
     * @param roleId
     * @return int 受影响的行数
     * @throws SQLException
     */
    @Override
    public int delRoleMenuByRoleId(int roleId) throws SQLException {
        return roleDao.delRoleMenuByRoleId(roleId);
    }

    /**
     * 新增角色菜单
     *
     * @param roleId
     * @param menuIds
     * @return int 受影响的行数
     * @throws SQLException
     */
    @Override
    public int addRoleMenu(int roleId, int[] menuIds) throws SQLException {
        return roleDao.addRoleMenu(roleId, menuIds);
    }

    /**
     * 根据角色id查询权限信息
     *
     * @param roleId
     * @return List
     */
    @Override
    public List<Map<String, Object>> getPermissionByRoleId(int roleId) {
        return roleDao.getPermissionByRoleId(roleId);
    }

    /**
     * 新增角色权限信息
     *
     * @param roleId
     * @param permissionIds
     * @return int 影响的行数
     * @throws SQLException
     */
    @Override
    public int addRolePermission(int roleId, int[] permissionIds) throws SQLException {
        return roleDao.addRolePermission(roleId, permissionIds);
    }

    /**
     * 根据角色id删除权限信息
     *
     * @param roleId
     * @return int 受影响的行数
     * @throws SQLException
     */
    @Override
    public int delRolePermissionByRoleId(int roleId) throws SQLException {
        return roleDao.delRolePermissionByRoleId(roleId);
    }

    @Override
    public boolean checkExist(Role role) {
        return roleDao.checkExist(role) > 0;
    }

    /**
     * 查询全部的角色信息
     *
     * @return List
     */
    @Override
    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }
}
