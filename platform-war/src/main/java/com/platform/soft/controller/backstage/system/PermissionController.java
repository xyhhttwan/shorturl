package com.platform.soft.controller.backstage.system;


import com.platform.soft.api.backstage.ex.IPermissionService;
import com.platform.soft.base.domain.ResponseMessage;
import com.platform.soft.base.domain.RspMsg;
import com.platform.soft.domain.backstage.ex.Permission;
import com.platform.soft.base.enums.MessageCode;
import com.platform.soft.base.springmvc.BackStageController;
import com.platform.soft.domain.backstage.ex.PermissionHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.List;

/**
 * 框架跳转
 *
 * @author baixb
 * @version [v1.0，2015/6/17]
 */
@Controller
@Scope("prototype")
@RequestMapping("/backstage/system/permissionManage")
public class PermissionController extends BackStageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuController.class);
    @Autowired
    private IPermissionService permissionService;

    private ResponseMessage rspMsg = new ResponseMessage();

    @RequestMapping("index")
    public String index() {

        return "permission/list";
    }

    @RequestMapping("/list")
    @ResponseBody
    public List list() {

        List<Permission> roleList = permissionService.getPermissionPageList(null);

        return roleList;
    }

    @RequestMapping("/addPermissionView")
    @RequiresPermissions("permission-add")
    public String addPermissionView() {
        String id = request.getParameter("id");
        request.setAttribute("id", id);
        return "permission/add";

    }

    @RequestMapping("/addPermission")
    @ResponseBody
    @RequiresPermissions("permission-add")
    public RspMsg addPermission(Permission permission) {

        try {
            permissionService.addPermission(permission);
            rspMsg.setIsSuccess(true);
            rspMsg.setMsgCode(MessageCode.DEFAULT_SUCCESS);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            rspMsg.setIsSuccess(false);
            rspMsg.setMsgCode(MessageCode.ADD_FAILED);
        }

        return rspMsg;

    }

    @RequestMapping("/updatePermissionView")
    @RequiresPermissions("permission-update")
    public String updatePermissionView(int id) {
        Permission pp = permissionService.getPermissionById(id);
        request.setAttribute("permission", pp);
        return addPermissionView();
    }

    @RequestMapping("/updatePermission")
    @ResponseBody
    @RequiresPermissions("permission-update")
    public RspMsg updatePermission(Permission permission) {
        try {
            permissionService.updatePermission(permission);
            rspMsg.setIsSuccess(true);
            rspMsg.setMsgCode(MessageCode.DEFAULT_SUCCESS);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            rspMsg.setIsSuccess(false);
            rspMsg.setMsgCode(MessageCode.UPDATE_FAILED);
        }
        return rspMsg;
    }

    @ResponseBody
    @RequestMapping("delPermission")
    @RequiresPermissions("permission-delete")
    public RspMsg delPermission(String ids) {

        try {
            if (!StringUtils.isEmpty(ids)) {
                int id = Integer.parseInt(ids);
                permissionService.delPermissionById(id);
            }
            rspMsg.setIsSuccess(true);
            rspMsg.setMsgCode(MessageCode.DEFAULT_SUCCESS);
        } catch (SQLException e) {
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
    @RequestMapping("getPermissionAll")
    @ResponseBody
    public List<PermissionHelper> getPermissionAll() {

        List<PermissionHelper> list = permissionService.getPermissionAll();
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

        boolean result = permissionService.ifHasChildren(id);
        if (!result) {
            rspMsg.setIsSuccess(false);
            return rspMsg;
        }
        rspMsg.setIsSuccess(true);
        rspMsg.setMsgCode(MessageCode.MENU_HAS_CHILDREN);
        return rspMsg;

    }
}
