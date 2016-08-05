package com.platform.soft.controller.backstage.system;

import com.platform.soft.api.backstage.ex.IMenuService;
import com.platform.soft.base.domain.ResponseMessage;
import com.platform.soft.base.enums.MessageCode;
import com.platform.soft.base.domain.RspMsg;
import com.platform.soft.base.springmvc.BackStageController;
import com.platform.soft.domain.backstage.ex.Menu;
import com.platform.soft.domain.backstage.ex.MenuHelper;
import com.platform.soft.utils.WebUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述
 *
 * @author baixb
 * @version [v1.0，2015/6/17]
 */
@Controller
@Scope("prototype")
@RequestMapping("/backstage/system/menuManage")
public class MenuController extends BackStageController {


    private static final Logger LOGGER = LoggerFactory.getLogger(MenuController.class);

    private ResponseMessage rspMsg = new ResponseMessage();

    @Autowired
    private IMenuService menuService;

    @ResponseBody
    @RequestMapping("/menuList")
    public Map<String, Object> menuList() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Menu> list = menuService.getMenuList(WebUtils.getUserId(request));
        map.put("menus", list);
        return map;
    }

    /**
     * 连接跳转
     *
     * @return String
     */
    @RequestMapping("index")
    public String index() {
        return "menu/list";
    }


    @RequestMapping("list")
    @ResponseBody
    public List<Menu> list() {
        List<Menu> list = menuService.getAllMenuList();
        return list;
    }

    @RequestMapping("/addMenuView")
    @RequiresPermissions("menu-add")
    public String addMenuView() {
        String id = request.getParameter("id");
        request.setAttribute("id", id);
        return "menu/add";

    }

    @RequestMapping("/addMenu")
    @ResponseBody
    @RequiresPermissions("menu-add")
    public RspMsg addMenu(Menu menu) {


        try {
            menuService.addMenu(menu);
            rspMsg.setIsSuccess(true);
            rspMsg.setMsgCode(MessageCode.DEFAULT_SUCCESS);

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            rspMsg.setIsSuccess(false);
            rspMsg.setMsgCode(MessageCode.ADD_FAILED);
        }

        return rspMsg;

    }


    @RequestMapping("/updateMenuView")
    @RequiresPermissions("menu-update")
    public String updateMenuView(int id) {
        Menu menu = menuService.getMenuById(id);
        request.setAttribute("menu", menu);
        return addMenuView();
    }

    @RequestMapping("/updateMenu")
    @ResponseBody
    @RequiresPermissions("menu-update")
    public RspMsg updateMenu(Menu menu) {
        try {
            menuService.updateMenu(menu);
            rspMsg.setIsSuccess(true);
            rspMsg.setMsgCode(MessageCode.DEFAULT_SUCCESS);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            rspMsg.setIsSuccess(false);
            rspMsg.setMsgCode(MessageCode.UPDATE_FAILED);
        }
        return rspMsg;
    }

    @ResponseBody
    @RequestMapping("delMenu")
    @RequiresPermissions("menu-delete")
    public RspMsg delMenu(String ids) {

        try {
            if (!StringUtils.isEmpty(ids)) {
                int id = Integer.parseInt(ids);
                menuService.delMenu(id);
            }
            rspMsg.setIsSuccess(true);
            rspMsg.setMsgCode(MessageCode.DEFAULT_SUCCESS);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            rspMsg.setIsSuccess(false);
            rspMsg.setMsgCode(MessageCode.DELETE_FAILED);
        }
        return rspMsg;
    }

    /**
     * 下拉框用到 只查询id和name
     *
     * @return List
     */
    @RequestMapping("getMenuAll")
    @ResponseBody
    public List<MenuHelper> getMenunAll() {

        List<MenuHelper> list = menuService.getMenuBySelect();
        return list;
    }

    /**
     * 判断是否有子类
     *
     * @param id
     * @return RspMsg
     */
    @ResponseBody
    @RequestMapping("/ifHasChildren")
    public RspMsg ifHasChildren(int id) {

        boolean result = menuService.ifMenuHasChildren(id);
        if (!result) {
            rspMsg.setIsSuccess(false);
            return rspMsg;
        }
        rspMsg.setIsSuccess(true);
        rspMsg.setMsgCode(MessageCode.MENU_HAS_CHILDREN);
        return rspMsg;

    }

    @RequestMapping("/getAllMenus")
    @ResponseBody
    public List<Menu> getAllMenus() {
        return menuService.getAllMenuList();
    }


}
