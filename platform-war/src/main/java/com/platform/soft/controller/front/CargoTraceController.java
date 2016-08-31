package com.platform.soft.controller.front;

import com.google.common.collect.Maps;
import com.platform.soft.api.backstage.IDynamicOrderService;
import com.platform.soft.api.backstage.INavigationService;
import com.platform.soft.api.backstage.IOrderService;
import com.platform.soft.domain.backstage.DynamicOrder;
import com.platform.soft.domain.backstage.Order;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by baixiaobin on 16/8/29.
 */
@Controller
@RequestMapping("/html/")
public class CargoTraceController {


    @Autowired
    private IDynamicOrderService dynamicOrderService;

    @Autowired
    private INavigationService navigationService;

    @Autowired
    private IOrderService orderService;

    @RequestMapping(value = "/cargo_trace", method = RequestMethod.GET)
    public String CargoTrace(HttpServletRequest request, @RequestParam(value = "orderId", required = false) String orderId) {
        //导航
        getNavigation(request);

        if(!StringUtils.isNumeric(orderId) || orderId.length()<14){
            return "html/cargo_trace";
        }

        getData(orderId,request);

        return "html/cargo_trace";
    }


    private void getData(String orderId,HttpServletRequest request){
        if (StringUtils.isNotBlank(orderId)) {

            Map<String, Object> map = Maps.newHashMap();
            map.clear();
            map.put("orderId", orderId);
            Order order = (Order) orderService.queryOne(map);
            if (order != null) {
                map.clear();
                map.put("orderId", order.getOrderId());
                List<DynamicOrder> list = dynamicOrderService.queryList(map, "arriveTime", "desc", null);
                request.setAttribute("orderCloseDate", new Date(order.getLastModDate()));
                request.setAttribute("list", list);
            }
            request.setAttribute("order", order);
        }
        request.setAttribute("orderId", orderId);
    }

    @RequestMapping(value = "/orderSearch", method = RequestMethod.POST)
    public String orderSearch(HttpServletRequest request, @RequestParam(value = "orderId", required = true)String orderId){
        getData(orderId,request);
       return  "html/ajax/result";
    }

    private void getNavigation(HttpServletRequest request) {
        //导航
        Map<String, Object> map = Maps.newHashMap();
        map.put("status", "1");
        request.setAttribute("navigation", navigationService.queryList(map, "id", "asc"));
        request.setAttribute("select", 1);
    }
}
