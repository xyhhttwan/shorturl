package com.platform.soft.service.service.backstage.ex;


import com.platform.soft.api.backstage.ex.IMenuService;
import com.platform.soft.service.dao.backstage.ex.IMenuDAO;
import com.platform.soft.domain.backstage.ex.Menu;
import com.platform.soft.domain.backstage.ex.MenuHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述
 *
 * @author baixb
 * @version [v1.0，2015/6/17]
 */
@Service
public class MenuServiceImpl implements IMenuService {

    @Autowired
    private IMenuDAO menuDao;
    /**
     * 根据用户id过滤属于当前登录用户的菜单资源
     *
     * @param systemUserId
     * @return List<Menu>
     */
    @Override
    public List<Menu> getMenuList(int systemUserId) {

        List<Menu> list =  menuDao.getMenuList(systemUserId);
        List<Menu> resultList = new ArrayList<Menu>();

        for(Menu menu :list){
            if(null==menu.getParentId() || menu.getParentId()==0){
                recursionMenu(list,menu);
                resultList.add(menu);
            }
        }
        return resultList;
    }

    /**
     * 新增菜单
     *
     * @param menu
     * @return int 受影响的行数
     * @throws SQLException
     */
    @Override
    public int addMenu(Menu menu) throws SQLException {
        return menuDao.addMenu(menu);
    }

    /**
     * 修改菜单
     *
     * @param menu
     * @return int 受影响的行数
     * @throws SQLException
     */
    @Override
    public int updateMenu(Menu menu) throws SQLException {
        return menuDao.updateMenu(menu);
    }

    /**
     * 删除菜单
     *
     * @param id
     * @return int 受影响的行数
     * @throws SQLException
     */
    @Override
    public int delMenu(int id) throws SQLException {
        return menuDao.delMenu(id);
    }

    /**
     * 查询全部的菜单
     *
     * @return List
     */
    @Override
    public List<Menu> getAllMenuList() {
        List<Menu> list = menuDao.getAllMenuList();
        List<Menu> resultList = new ArrayList<Menu>();

        for(Menu menu :list){
            if(null==menu.getParentId()){
                recursionMenu(list,menu);
                resultList.add(menu);
            }
        }
        return resultList;
    }

    /**
     * 下拉框树用到
     *
     * @return List
     */
    @Override
    public List<MenuHelper> getMenuBySelect() {
        List<MenuHelper> list =  menuDao.getMenuBySelect();
        List<MenuHelper> resultList = new ArrayList<MenuHelper>();
        for(MenuHelper menuHelper :list){
            if(null==menuHelper.getParentId()){
                recursionMenuHlper(list, menuHelper);
                resultList.add(menuHelper);
            }
        }
        return resultList;
    }

    /**
     * 查询该菜单下存不存在子菜单
     *
     * @param id
     * @return List
     */
    @Override
    public boolean ifMenuHasChildren(int id) {
        List<?> list =  menuDao.ifMenuHasChildren(id);
        if(null !=list && list.size()>0){
            return true;
        }
        return  false;
    }

    /**
     * 根据id查询菜单
     *
     * @param id
     * @return List
     */
    @Override
    public Menu getMenuById(int id) {
        return menuDao.getMenuById(id);
    }


    /**
     * 递归菜单
     * @param menuList
     * @param menu
     */
    private void  recursionMenu(List<Menu> menuList,Menu menu){
        List<Menu> menuList_ =new ArrayList<Menu>();
        for(Menu menus :menuList){
            if(null!=menus.getParentId()){
                if(menu.getId()==menus.getParentId()){
                    menuList_.add(menus);
                    recursionMenu(menuList,menus);
                }
            }

        }
        menu.setChildren(menuList_);
    }
    /**
     * 递归菜单
     * @param menuList
     * @param menuHelper
     */
    private void  recursionMenuHlper(List<MenuHelper> menuList,MenuHelper menuHelper){
        List<MenuHelper> menuList_ =new ArrayList<MenuHelper>();
        for(MenuHelper menus :menuList){
            if(null!=menus.getParentId()){
                if(menuHelper.getId()==menus.getParentId()){
                    menuList_.add(menus);
                    recursionMenuHlper(menuList,menus);
                }
            }
        }
        menuHelper.setChildren(menuList_);
    }

}
