package com.platform.soft.controller.backstage;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.platform.soft.api.backstage.IOrderService;
import com.platform.soft.base.domain.Constants;
import com.platform.soft.base.domain.ResponseMessage;
import com.platform.soft.base.enums.MessageCode;
import com.platform.soft.base.springmvc.BackStageController;
import com.platform.soft.common.domain.SearchField;
import com.platform.soft.common.utils.Status;
import com.platform.soft.domain.backstage.OnlineOrder;
import com.platform.soft.domain.backstage.Order;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
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
    public Map<String, Object> list(@RequestParam(value = "status", defaultValue = "0") int status) {
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


        Map<String,Object> map = Maps.newHashMap();
        map.put("orderId",order.getOrderId());
        map.put("groupOp", "and");
        map.put("orderId",new SearchField("orderId", "=", order.getOrderId()));
        int count = orderService.count(map);
        if(count>0){
            rsgMsg.setIsSuccess(false);
            rsgMsg.setMsgCode(MessageCode.ORDER_IS_EXIST);
            return rsgMsg;
        }
        try {
            if(null==order.getOrderTime()){
                order.setOrderTime(new Date());
            }
            order.setStatus(Status.NORMAL.getIndex());
            orderService.insert(order);
            rsgMsg.setIsSuccess(true);
            rsgMsg.setMsgCode(MessageCode.DEFAULT_SUCCESS);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            rsgMsg.setIsSuccess(false);
            rsgMsg.setMsgCode(MessageCode.DEFAULT_FAILED);
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

    @RequestMapping("/closeOrder")
    @RequiresPermissions("order-close")
    @ResponseBody
    public ResponseMessage closeOrder(Integer ids) {
        ResponseMessage rsgMsg = new ResponseMessage();

        Map<String, Object> updateMap = Maps.newHashMap();
        Map<String, Object> conditionMap = Maps.newHashMap();
        updateMap.put("status",1);
        conditionMap.put("id",ids);
        try {
            orderService.updateByCondition(updateMap,conditionMap);
            rsgMsg.setIsSuccess(true);
            rsgMsg.setMsgCode(MessageCode.DEFAULT_SUCCESS);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            rsgMsg.setIsSuccess(false);
            rsgMsg.setMsgCode(MessageCode.UPDATE_FAILED);
        }
        return rsgMsg;
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
