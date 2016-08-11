package com.platform.soft.controller.backstage;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.platform.soft.api.backstage.IOnlineOrderService;
import com.platform.soft.base.domain.ResponseMessage;
import com.platform.soft.base.enums.MessageCode;
import com.platform.soft.base.springmvc.BackStageController;
import com.platform.soft.common.utils.Status;
import com.platform.soft.domain.backstage.OnlineOrder;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *后台在线下单
 */
@Controller
@Scope("prototype")
@RequestMapping("/backstage/onlineOrder/onlineOrderManage")
public class OnlineOrderController extends BackStageController{

    private static final Logger LOGGER = LoggerFactory.getLogger(OnlineOrderController.class);

    @Autowired
    private IOnlineOrderService onlineOrderService;


    @RequestMapping("/index")
    public String index() {
        return "onlineOrder/list";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(@RequestParam(value = "isLink",defaultValue ="0" ,required = false) int isLink) {
        initPageRows();
        PageHelper.startPage(page, rows);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", Status.NORMAL.getIndex());
        map.put("isLink", isLink);

        List<OnlineOrder> onlineOrderList = onlineOrderService.queryList(map, "createDate", "asc");
        PageInfo<List<OnlineOrder>> pageList = new PageInfo(onlineOrderList);
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
    @RequestMapping("/addView")
    @RequiresPermissions("onlineOrder-add")
    public String addView() {

        return "onlineOrder/add";
    }


    /**
     * 新增
     *
     * @return String
     */
    @RequestMapping("/add")
    @ResponseBody
    @RequiresPermissions("onlineOrder-add")
    public ResponseMessage add(OnlineOrder onlineOrder) {
        ResponseMessage rsgMsg = new ResponseMessage();

        try {
            onlineOrderService.insert(onlineOrder);
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

    @RequestMapping("/updateView")
    @RequiresPermissions("onlineOrder-update")
    public String updateView(Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        OnlineOrder onlineOrder = (OnlineOrder) onlineOrderService.queryOne(map);
        map.put("id", id);
        request.setAttribute("onlineOrder", onlineOrder);
        return "onlineOrder/add";
    }

    @RequestMapping("/update")
    @ResponseBody
    @RequiresPermissions("onlineOrder-update")
    public ResponseMessage update(OnlineOrder onlineOrder) {
        ResponseMessage rsgMsg = new ResponseMessage();

        try {
            onlineOrderService.update(onlineOrder);
            rsgMsg.setIsSuccess(true);
            rsgMsg.setMsgCode(MessageCode.DEFAULT_SUCCESS);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            rsgMsg.setIsSuccess(false);
            rsgMsg.setMsgCode(MessageCode.UPDATE_FAILED);
        }
        return rsgMsg;
    }


    @RequestMapping("/delete")
    @ResponseBody
    @RequiresPermissions("onlineOrder-delete")
    public ResponseMessage delete(String ids) {
        ResponseMessage rsgMsg = new ResponseMessage();
        try {
            onlineOrderService.deleteById(ids);
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
