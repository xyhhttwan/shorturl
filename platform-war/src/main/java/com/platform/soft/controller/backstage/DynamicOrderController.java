package com.platform.soft.controller.backstage;

import com.google.common.collect.Maps;
import com.platform.soft.api.backstage.IDynamicOrderService;
import com.platform.soft.api.backstage.IOrderService;
import com.platform.soft.base.domain.ResponseMessage;
import com.platform.soft.base.enums.MessageCode;
import com.platform.soft.base.springmvc.BackStageController;
import com.platform.soft.common.domain.SearchField;
import com.platform.soft.common.utils.Status;
import com.platform.soft.domain.backstage.DynamicOrder;
import com.platform.soft.domain.backstage.Order;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单的更新
 */
@Controller
@Scope("prototype")
@RequestMapping("/backstage/dynamicOrder/dynamicOrderManage")
public class DynamicOrderController extends BackStageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicOrderController.class);


    @Autowired
    private IDynamicOrderService dynamicOrderService;

    @Autowired
    private IOrderService orderService;


    /**
     * 新增跳转
     *
     * @return String
     */
    @RequestMapping("/addView")
    @RequiresPermissions("dynamicOrder-add")
    public String addView(Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id",id);
        Order order = (Order) orderService.queryOne(map);
        request.setAttribute("now",new Date());
        request.setAttribute("order", order);
        return "dynamicOrder/add";
    }


    /**
     * 新增
     *
     * @return String
     */
    @RequestMapping("/add")
    @ResponseBody
    @RequiresPermissions("dynamicOrder-add")
    public ResponseMessage add(DynamicOrder dynamicOrder) {
        ResponseMessage rsgMsg = new ResponseMessage();

        try {
             dynamicOrderService.insert(dynamicOrder);
            rsgMsg.setIsSuccess(true);
            rsgMsg.setMsgCode(MessageCode.DEFAULT_SUCCESS);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            rsgMsg.setIsSuccess(false);
            rsgMsg.setMsgCode(MessageCode.DEFAULT_FAILED);
        }
        return rsgMsg;
    }

    @RequestMapping("/view")
    @RequiresPermissions("dynamicOrder-view")
    public String view(Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id",id);
        Order order = (Order) orderService.queryOne(map);
        if(order !=null){
            map.clear();
            map.put("orderId",order.getOrderId());
            List<DynamicOrder> list = dynamicOrderService.queryList(map,"arriveTime","desc",null);
            request.setAttribute("list",list);
        }
        request.setAttribute("order", order);
        request.setAttribute("orderCloseDate",new Date(order.getLastModDate()));
        return "dynamicOrder/view";
    }

}
