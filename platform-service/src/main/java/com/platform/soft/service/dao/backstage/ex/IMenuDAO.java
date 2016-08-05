package com.platform.soft.service.dao.backstage.ex;



import com.platform.soft.domain.backstage.ex.Menu;
import com.platform.soft.domain.backstage.ex.MenuHelper;

import java.sql.SQLException;
import java.util.List;

/**
 * 菜单dao接口
 * 目前只实现查询功能
 * @author baixb
 * @version [v1.0，2015/6/17]
 */
public interface IMenuDAO {

    /**
     * 根据用户id过滤属于当前登录用户的菜单资源
     * @param systemUserId
     * @return List<Menu>
     */
     List<Menu> getMenuList(int systemUserId);

    /**
     * 新增菜单
     * @param menu
     * @return int 受影响的行数
     * @throws SQLException
     */
     int addMenu(Menu menu)throws SQLException;

    /**
     * 修改菜单
     * @param menu
     * @return int 受影响的行数
     * @throws SQLException
     */
     int updateMenu(Menu menu)throws SQLException;

    /**
     * 删除菜单
     * @param id
     * @return int 受影响的行数
     * @throws SQLException
     */
     int  delMenu(int id)throws SQLException;

    /**
     * 查询全部的菜单
     * @return List
     */
     List<Menu> getAllMenuList();

    /**
     * 下拉框树用到
     * @return List
     */
     List <MenuHelper> getMenuBySelect();

    /**
     * 查询该菜单下存不存在子菜单
     * @param id
     * @return List
     */
     List<?> ifMenuHasChildren(int id);

    /**
     * 根据id查询菜单
     * @param id
     * @return List
     */
    Menu getMenuById(int id);


}
