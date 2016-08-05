package com.platform.soft.service.service.backstage.ex;

import com.platform.soft.api.backstage.ex.IPermissionService;
import com.platform.soft.domain.backstage.ex.Permission;
import com.platform.soft.domain.backstage.ex.PermissionHelper;
import com.platform.soft.service.dao.backstage.ex.IPermissionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 权限service层实现类
 *
 * @author baixb
 * @version [v1.0，2015/6/18]
 */
@Service("permissionService")
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private IPermissionDAO permissionDao;

    /**
     * 新增权限信息
     *
     * @param permission
     * @return int 受影响的行数
     * @throws SQLException
     */
    @Override
    public int addPermission(Permission permission) throws SQLException {
        return permissionDao.addPermission(permission);
    }

    /**
     * 修改权限信息
     *
     * @param permission
     * @return int 受影响的行数
     * @throws SQLException
     */
    @Override
    public int updatePermission(Permission permission) throws SQLException {
        return permissionDao.updatePermission(permission);
    }

    /**
     * 删除权限信息
     *
     * @param id
     * @return int 受影响的行数
     * @throws SQLException
     */
    @Override
    public int delPermissionById(int id) throws SQLException {
        return permissionDao.delPermissionById(id);
    }

    /**
     * 查询权限信息分页
     *
     * @param map
     * @return List
     */
    @Override
    public List<Permission> getPermissionPageList(Map<String, Object> map) {
        List<Permission> list = permissionDao.getPermissionPageList(map);
        List<Permission> resultList = new ArrayList<Permission>();
        for (Permission permission : list) {
            if (permission.getParentId() == 0) {
                recursionMenu(list, permission);
                resultList.add(permission);
            }
        }
        return resultList;
    }

    /**
     * 递归菜单
     *
     * @param PermissionList
     * @param permission
     */
    private void recursionMenu(List<Permission> PermissionList, Permission permission) {
        List<Permission> permissionList_ = new ArrayList<Permission>();
        for (Permission permissions : PermissionList) {
            if (permission.getId() == permissions.getParentId()) {
                permissionList_.add(permissions);
                recursionMenu(PermissionList, permissions);
            }

        }
        permission.setChildren(permissionList_);
    }

    /**
     * 根据id查询权限信息
     *
     * @param id
     * @return Permission
     */
    @Override
    public Permission getPermissionById(int id) {
        return permissionDao.getPermissionById(id);
    }

    /**
     * 下拉框用到 只查询id和name
     *
     * @return list
     */
    @Override
    public List<PermissionHelper> getPermissionAll() {
        List<PermissionHelper> list = permissionDao.getPermissionAll();
        List<PermissionHelper> resultList = new ArrayList<PermissionHelper>();
        for (PermissionHelper permissionHelper : list) {
            if (permissionHelper.getParentId() == 0) {
                recursionMenu(list, permissionHelper);
                resultList.add(permissionHelper);
            }
        }
        return resultList;
    }

    /**
     * 判断是否有子类
     *
     * @param id
     * @return boolean
     */
    @Override
    public boolean ifHasChildren(int id) {
        List<?> list = permissionDao.ifHasChildren(id);
        if (null != list && list.size() > 0) {
            return true;
        }
        return false;
    }

    /**
     * 根据用户id查找该用户的权限信息
     *
     * @param userId
     * @return List
     */
    @Override
    public List<Permission> getPermissionByUserId(int userId) {
        return permissionDao.getPermissionByUserId(userId);
    }

    @Override
    public Permission getPermissionByCode(String code) {
        return permissionDao.getPermissionByCode(code);
    }

    @Override
    public List<Permission> getAllPermissionList() {
        return permissionDao.getPermissionPageList(new HashMap<String, Object>());
    }

    /**
     * 递归菜单
     *
     * @param PermissionList
     * @param permission
     */
    private void recursionMenu(List<PermissionHelper> PermissionList, PermissionHelper permission) {
        List<PermissionHelper> permissionList_ = new ArrayList<PermissionHelper>();
        for (PermissionHelper permissions : PermissionList) {
            if (permission.getId() == permissions.getParentId()) {
                permissionList_.add(permissions);
                recursionMenu(PermissionList, permissions);
            }

        }
        permission.setChildren(permissionList_);
    }
}
