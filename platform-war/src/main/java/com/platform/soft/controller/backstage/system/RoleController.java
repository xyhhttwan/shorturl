package com.platform.soft.controller.backstage.system;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.platform.soft.api.backstage.ex.IPermissionService;
import com.platform.soft.api.backstage.ex.IRoleService;
import com.platform.soft.base.domain.ResponseMessage;
import com.platform.soft.base.enums.MessageCode;
import com.platform.soft.base.springmvc.BackStageController;
import com.platform.soft.domain.backstage.ex.Permission;
import com.platform.soft.domain.backstage.ex.Role;
import com.platform.soft.utils.WebUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaohaiyang on 2015/6/19.
 */

@Controller
@Scope("prototype")
@RequestMapping("/backstage/system/roleManage")
public class RoleController extends BackStageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/index")
    public String index() {
        return "role/list";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(String roleName) {
        initPageRows();
        PageHelper.startPage(page, rows);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("roleName", roleName);
        List<Role> roleList = roleService.getRolePageList(map);
        PageInfo<List<Role>> pageList = new PageInfo(roleList);
        map.clear();
        map.put("total", pageList.getTotal());
        map.put("rows", pageList.getList());
        return map;
    }


    /**
     * 新增跳转
     *
     * @return String
     */
    @RequestMapping("/addRoleView")
    @RequiresPermissions("role-add")
    public String addUserView() {

        return "role/add";
    }


    /**
     * 新增
     *
     * @return String
     */
    @RequestMapping("/addRole")
    @ResponseBody
    @RequiresPermissions("role-add")
    public ResponseMessage addRole(Role role) {
        ResponseMessage rsgMsg = new ResponseMessage();
        boolean isExist = roleService.checkExist(role);
        if (isExist) {
            rsgMsg.setIsSuccess(false);
            rsgMsg.setMsgCode(MessageCode.ROLE_NAME_IS_EXIST);

        } else
            try {
                roleService.addRole(role);
                rsgMsg.setIsSuccess(true);
                rsgMsg.setMsgCode(MessageCode.DEFAULT_SUCCESS);

            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
                rsgMsg.setIsSuccess(false);
                rsgMsg.setMsgCode(MessageCode.ADD_FAILED);
            }
        return rsgMsg;
    }


    /**
     * 修改跳转
     *
     * @return
     */

    @RequestMapping("/updateRoleView")
    @RequiresPermissions("role-update")
    public String updateRoleView(Integer id) {
        Role role = roleService.getRoleById(id);
        request.setAttribute("role", role);
        return "role/add";
    }

    @RequestMapping("/updateRole")
    @ResponseBody
    @RequiresPermissions("role-update")
    public ResponseMessage updateRole(Role role) {
        ResponseMessage rsgMsg = new ResponseMessage();
        boolean isExist = roleService.checkExist(role);
        if (isExist) {
            rsgMsg.setIsSuccess(false);
            rsgMsg.setMsgCode(MessageCode.ROLE_NAME_IS_EXIST);

        } else
            try {
                roleService.updateRole(role);
                rsgMsg.setIsSuccess(true);
                rsgMsg.setMsgCode(MessageCode.DEFAULT_SUCCESS);
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
                rsgMsg.setIsSuccess(false);
                rsgMsg.setMsgCode(MessageCode.UPDATE_FAILED);
            }
        return rsgMsg;
    }


    @RequestMapping("/deleteRole")
    @ResponseBody
    @RequiresPermissions("role-delete")
    public ResponseMessage deleteRole(String ids) {
        ResponseMessage rsgMsg = new ResponseMessage();
        try {
            roleService.delRole(ids);
            rsgMsg.setIsSuccess(true);
            rsgMsg.setMsgCode(MessageCode.DEFAULT_SUCCESS);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            rsgMsg.setIsSuccess(false);
            rsgMsg.setMsgCode(MessageCode.DELETE_FAILED);
        }
        return rsgMsg;
    }

    /**
     * 查询全部的角色信息
     *
     * @return
     */
    @RequestMapping("/getAllRoles")
    @ResponseBody
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    /**
     * 设置菜单跳转页面
     *
     * @param id
     * @return String
     */
    @RequestMapping("/setMenuView")
    @RequiresPermissions("role-setMenu")
    public String setMenuView(int id) {
        request.setAttribute("role", roleService.getRoleById(id));
        request.setAttribute("userMenu", roleService.getMenuByRoleId(id));
        return "role/role_menu";
    }

    /**
     * 设置菜单
     *
     * @param roleId
     * @param menuIds
     * @return RspMsg
     */
    @RequestMapping("/setMenu")
    @ResponseBody
    @RequiresPermissions("role-setMenu")
    public ResponseMessage setMenu(int roleId, int menuIds[]) {
        ResponseMessage rsgMsg = new ResponseMessage();
        try {
            roleService.addRoleMenu(roleId, menuIds);
            rsgMsg.setIsSuccess(true);
            rsgMsg.setMsgCode(MessageCode.DEFAULT_SUCCESS);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            rsgMsg.setIsSuccess(false);
            rsgMsg.setMsgCode(MessageCode.MENU_SETTING_FAILED);

        }
        return rsgMsg;
    }

    /**
     * 设置权限跳转页面
     *
     * @param id
     * @return String
     */
    @RequestMapping("/setPermissionView")
    @RequiresPermissions("role-setPermission")
    public String setPermissionView(int id) {
        Role role = roleService.getRoleById(id);
        List<Map<String, Object>> pp = roleService.getPermissionByRoleId(id);
        request.setAttribute("permission", pp);
        request.setAttribute("role", role);
        return "role/role_permission";
    }

    @RequestMapping("/setPermission")
    @ResponseBody
    @RequiresPermissions("role-setPermission")
    public ResponseMessage setPermission(int roleId, int permissionIds[]) {
        ResponseMessage rsgMsg = new ResponseMessage();
        try {
            roleService.addRolePermission(roleId, permissionIds);
            saveSessionRolePermission();
            rsgMsg.setIsSuccess(true);
            rsgMsg.setMsgCode(MessageCode.DEFAULT_SUCCESS);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            rsgMsg.setIsSuccess(false);
            rsgMsg.setMsgCode(MessageCode.PERMISSION_SETTING_FAILED);
        }
        return rsgMsg;
    }

    @Async
    private void  saveSessionRolePermission(){
        List<Permission> permissionList = permissionService.getPermissionByUserId(WebUtils.getUserId(request));
        Subject currentUser = SecurityUtils.getSubject();
        if(null != currentUser){
            Session session = currentUser.getSession();
            if(null != session){
                session.setAttribute("permissionList", permissionList);
            }
        }
    }
}

