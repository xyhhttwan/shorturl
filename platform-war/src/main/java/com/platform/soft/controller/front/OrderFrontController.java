package com.platform.soft.controller.front;

import com.platform.soft.api.backstage.IDynamicOrderService;
import com.platform.soft.api.backstage.IOrderService;
import com.platform.soft.base.springmvc.BackStageController;
import com.platform.soft.domain.backstage.DynamicOrder;
import com.platform.soft.domain.backstage.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by baixiaobin on 16/8/15.
 */
@Controller
@RequestMapping("/html/order/")
public class OrderFrontController extends BackStageController{

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderFrontController.class);


    @Autowired
    private IDynamicOrderService dynamicOrderService;

    @Autowired
    private IOrderService orderService;

    @RequestMapping(value = "/{orderId}",method = RequestMethod.GET)
    public String searchOrder(@PathVariable String orderId){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id",orderId);
        Order order = (Order) orderService.queryOne(map);
        if(order !=null){
            map.clear();
            map.put("orderId",order.getOrderId());
            List<DynamicOrder> list = dynamicOrderService.queryList(map,"arriveTime","desc",null);
            request.setAttribute("orderCloseDate",new Date(order.getLastModDate()));
            request.setAttribute("list",list);
        }
        request.setAttribute("order", order);

        return "html/order";
    }
}
