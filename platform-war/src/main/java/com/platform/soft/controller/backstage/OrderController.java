package com.platform.soft.controller.backstage;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.platform.soft.api.backstage.IOrderService;
import com.platform.soft.base.domain.ResponseMessage;
import com.platform.soft.base.enums.MessageCode;
import com.platform.soft.base.springmvc.BackStageController;
import com.platform.soft.common.utils.Status;
import com.platform.soft.domain.backstage.OnlineOrder;
import com.platform.soft.domain.backstage.Order;
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
 * Created by baixiaobin on 16/8/11.
 */
@Controller
@Scope("prototype")
@RequestMapping("/backstage/order/orderManage")
public class OrderController extends BackStageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private IOrderService orderService;


    @RequestMapping("/index")
    public String index() {
        return "order/list";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(int status) {
        initPageRows();
        PageHelper.startPage(page, rows);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", status);

        List<Order> orderList = orderService.queryList(map, "id", "asc");
        PageInfo<List<Order>> pageList = new PageInfo(orderList);
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
    @RequiresPermissions("order-add")
    public String addView() {

        return "order/add";
    }


    /**
     * 新增
     *
     * @return String
     */
    @RequestMapping("/add")
    @ResponseBody
    @RequiresPermissions("order-add")
    public ResponseMessage add(Order order) {
        ResponseMessage rsgMsg = new ResponseMessage();

        try {
            order.setStatus(Status.NORMAL.getIndex());
            orderService.insert(order);
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
    @RequiresPermissions("order-update")
    public String updateView(Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        Order order = (Order) orderService.queryOne(map);
        map.put("id", id);
        request.setAttribute("order", order);
        return "order/add";
    }

    @RequestMapping("/update")
    @ResponseBody
    @RequiresPermissions("order-update")
    public ResponseMessage update(Order order) {
        ResponseMessage rsgMsg = new ResponseMessage();

        try {
            orderService.update(order);
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
    @RequiresPermissions("order-delete")
    public ResponseMessage delete(String ids) {
        ResponseMessage rsgMsg = new ResponseMessage();
        try {
            orderService.deleteById(ids);
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
