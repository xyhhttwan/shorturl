package com.platform.soft.controller.backstage.system;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.platform.soft.api.backstage.ex.ISystemUserService;
import com.platform.soft.base.domain.ResponseMessage;
import com.platform.soft.base.enums.MessageCode;
import com.platform.soft.base.domain.Constants;
import com.platform.soft.base.domain.RspMsg;
import com.platform.soft.base.springmvc.BackStageController;
import com.platform.soft.common.utils.encryptUtils.EncryptUtils;
import com.platform.soft.domain.backstage.ex.SystemUser;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统用户controller类
 *
 * @author baixb
 * @version [v1.0，2015-06-16]
 */

@Controller
@Scope("prototype")
@RequestMapping("backstage/system/")
public class SystemUserController extends BackStageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SystemUserController.class);

    private ResponseMessage rsgMsg = new ResponseMessage();
    @Autowired
    private ISystemUserService systemUserService;

    @RequestMapping("/userManage/index")
    public String index() {
        return "user/list";
    }

    /**
     * 用户列表
     *
     * @param userName
     * @param phone
     * @return Map
     */
    @RequestMapping("/userManage/list")
    @ResponseBody
    public Map<String, Object> list(String userName, String phone) {

        PageHelper.startPage(page, rows);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("systemName", userName);
        map.put("phone", phone);
        List<SystemUser> list = systemUserService.getUserPageList(map);
        PageInfo<List<Map<String, Object>>> pageList = new PageInfo(list);
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
    @RequiresPermissions("user-add")
    @RequestMapping("/userManage/addUserView")
    public String addUserView() {
        return "user/add";
    }

    /**
     * 新增
     *
     * @return String
     */
    @RequestMapping("/userManage/addUser")
    @ResponseBody
    @RequiresPermissions("user-add")
    public RspMsg addUser(SystemUser systemUser) {
        if (systemUserService.checkUserName(systemUser.getSystemName().trim())) {
            rsgMsg.setIsSuccess(false);
            rsgMsg.setMsgCode(MessageCode.USER_NAME_IS_EXIST);
            return rsgMsg;
        }

        try {
            systemUserService.addSystemUser(systemUser);
            rsgMsg.setIsSuccess(true);
            rsgMsg.setMsgCode(MessageCode.DEFAULT_SUCCESS);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(),e);
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
    @RequiresPermissions("user-update")
    @RequestMapping("/userManage/updateUserView")
    public String updateUserView(Integer id) {
        SystemUser user = systemUserService.getUserById(id);
        request.setAttribute("user", user);
        return "user/add";
    }


    /**
     * 修改跳转
     *
     * @return
     */
    @RequestMapping("/userManage/updateUser")
    @ResponseBody
    @RequiresPermissions("user-update")
    public RspMsg updateUserView(SystemUser systemUser) {

        try {
            systemUserService.updateUser(systemUser);
            rsgMsg.setIsSuccess(true);
            rsgMsg.setMsgCode(MessageCode.DEFAULT_SUCCESS);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(),e);
            rsgMsg.setIsSuccess(false);
            rsgMsg.setMsgCode(MessageCode.UPDATE_FAILED);
        }
        return rsgMsg;
    }

    /**
     * 删除用户
     *
     * @return RspMsg
     */
    @RequestMapping("/userManage/delUser")
    @ResponseBody
    @RequiresPermissions("user-delete")
    public RspMsg delUser(String ids) {
        try {
            systemUserService.delUser(ids);
            rsgMsg.setIsSuccess(true);
            rsgMsg.setMsgCode(MessageCode.DEFAULT_SUCCESS);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(),e);
            rsgMsg.setIsSuccess(false);
            rsgMsg.setMsgCode(MessageCode.DELETE_FAILED);
        }
        return rsgMsg;
    }

    @RequestMapping("userManage/setRoleView")
    @RequiresPermissions("user-setRole")
    public String setRole(int id) {

        SystemUser user = systemUserService.getUserById(id);
        request.setAttribute("user", user);
        request.setAttribute("userRole", systemUserService.getRoleListByUserId(id));
        return "user/user_role";
    }

    @RequestMapping("userManage/setRole")
    @ResponseBody
    @RequiresPermissions("user-setRole")
    public RspMsg setRole(int userId, int[] roleIds) {
        try {
            systemUserService.addUserRole(userId, roleIds);
            rsgMsg.setIsSuccess(true);
            rsgMsg.setMsgCode(MessageCode.DEFAULT_SUCCESS);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(),e);
            rsgMsg.setIsSuccess(false);
            rsgMsg.setMsgCode(MessageCode.SETTING_FAILED);
        }
        return rsgMsg;
    }

    /**
     * 修改用户密码
     *
     * @param newPassword
     * @param oldPassword
     * @return RspMsg
     */
    @RequestMapping("userManage/updateUserPassword")
    @ResponseBody
    public RspMsg updateUserPassword(String newPassword, String oldPassword) {
        Map<String, Object> map = new HashMap<String, Object>();
        SystemUser user = (SystemUser) request.getSession().getAttribute("currentUser");

        user = systemUserService.getUserById(user.getId());
        if (!user.getPassword().equals(EncryptUtils.encodeMD5String(oldPassword))) {
            rsgMsg.setIsSuccess(false);
            rsgMsg.setMsgCode(MessageCode.USER_PASSWORD_ERROR);
            return rsgMsg;
        }

        map.put("id", user.getId());
        map.put("password", newPassword);
        try {
            systemUserService.updateUserPwd(map);
            rsgMsg.setIsSuccess(true);
            rsgMsg.setMsgCode(MessageCode.DEFAULT_SUCCESS);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(),e);
            rsgMsg.setIsSuccess(false);
            rsgMsg.setMsgCode(MessageCode.UPDATE_FAILED);
        }
        return rsgMsg;
    }

    @Override
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat(Constants.DATE_FORMAT_TEMPLATE_2), true));
    }
}

