package com.platform.soft.controller.backstage;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.platform.soft.api.backstage.INavigationService;
import com.platform.soft.base.domain.ResponseMessage;
import com.platform.soft.base.enums.MessageCode;
import com.platform.soft.base.springmvc.BackStageController;
import com.platform.soft.base.springmvc.BaseController;
import com.platform.soft.common.utils.Status;
import com.platform.soft.domain.backstage.Navigation;
import com.platform.soft.domain.backstage.ex.Role;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Scope("prototype")
@RequestMapping("/backstage/navigation/navigationManage")
public class NavigationController extends BackStageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NavigationController.class);

    @Autowired
    private INavigationService navigationService;


    @RequestMapping("/index")
    public String index() {
        return "navigation/list";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list() {
        initPageRows();
        PageHelper.startPage(page, rows);
        Map<String, Object> map = new HashMap<String, Object>();

        //map.put("status", Status.NORMAL.getIndex());
        List<Navigation> list = navigationService.queryList(map, "id", "asc");
        PageInfo<List<Navigation>> pageList = new PageInfo(list);

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
    @RequestMapping("/addNavigationView")
    @RequiresPermissions("navigation-add")
    public String addNavigationView() {

        return "navigation/add";
    }


    /**
     * 新增
     *
     * @return String
     */
    @RequestMapping("/addNavigation")
    @ResponseBody
    @RequiresPermissions("navigation-add")
    public ResponseMessage addNavigation(Navigation navigation) {
        ResponseMessage rsgMsg = new ResponseMessage();

        try {
            navigationService.insert(navigation);
            rsgMsg.setIsSuccess(true);
            rsgMsg.setMsgCode(MessageCode.DEFAULT_SUCCESS);

        } catch (Exception e) {
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

    @RequestMapping("/updateNavigationView")
    @RequiresPermissions("navigation-update")
    public String updateNavigationView(Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        Navigation navigation = (Navigation) navigationService.queryOne(map);
        request.setAttribute("navigation", navigation);
        return "navigation/add";
    }

    @RequestMapping("/updateNavigation")
    @ResponseBody
    @RequiresPermissions("navigation-update")
    public ResponseMessage updateNavigationView(Navigation navigation) {
        ResponseMessage rsgMsg = new ResponseMessage();

        try {
            navigationService.update(navigation);
            rsgMsg.setIsSuccess(true);
            rsgMsg.setMsgCode(MessageCode.DEFAULT_SUCCESS);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            rsgMsg.setIsSuccess(false);
            rsgMsg.setMsgCode(MessageCode.UPDATE_FAILED);
        }
        return rsgMsg;
    }


    @RequestMapping("/deleteNavigation")
    @ResponseBody
    @RequiresPermissions("navigation-delete")
    public ResponseMessage deleteNavigation(String ids) {
        ResponseMessage rsgMsg = new ResponseMessage();
        try {
            navigationService.deleteById(ids);
            rsgMsg.setIsSuccess(true);
            rsgMsg.setMsgCode(MessageCode.DEFAULT_SUCCESS);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            rsgMsg.setIsSuccess(false);
            rsgMsg.setMsgCode(MessageCode.DELETE_FAILED);
        }
        return rsgMsg;
    }


    @RequestMapping("/setPublishOrUnPublish")
    @ResponseBody
    @RequiresPermissions("navigation-set")
    public ResponseMessage setPublishOrUnPublish(String ids,String status) {
        ResponseMessage rsgMsg = new ResponseMessage();

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("id",ids);
        map.put("status",status);
        try {
            navigationService.updateMap(map);
            rsgMsg.setIsSuccess(true);
            rsgMsg.setMsgCode(MessageCode.DEFAULT_SUCCESS);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            rsgMsg.setIsSuccess(false);
            rsgMsg.setMsgCode(MessageCode.DELETE_FAILED);
        }
        return rsgMsg;
    }




}
