package com.platform.soft.controller.front;

import com.google.common.collect.Maps;
import com.platform.soft.api.backstage.INavigationService;
import com.platform.soft.api.backstage.IOnlineOrderService;
import com.platform.soft.base.domain.ResponseMessage;
import com.platform.soft.base.domain.VerifyCode;
import com.platform.soft.base.enums.MessageCode;
import com.platform.soft.domain.backstage.DynamicOrder;
import com.platform.soft.domain.backstage.OnlineOrder;
import com.platform.soft.domain.backstage.Order;
import com.platform.soft.utils.WebUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by baixiaobin on 16/8/29.
 */
@Controller
@RequestMapping("/html/")
public class OnlineOrderFrontController {
    @Autowired
    private INavigationService navigationService;
    @Autowired
    private IOnlineOrderService onlineOrderService;

    @RequestMapping(value = "/online_order", method = RequestMethod.GET)
    public String onLineOrder(HttpServletRequest request) {
        //导航
        Map<String, Object> map = Maps.newHashMap();
        map.put("status", "1");
        request.setAttribute("navigation", navigationService.queryList(map, "id", "asc"));
        request.setAttribute("select", 2);
        return "html/online_order";
    }

    @ResponseBody
    @RequestMapping(value = "/add_online_order", method = RequestMethod.POST)
    public ResponseMessage AddOnLineOrder(HttpServletRequest request, OnlineOrder onlineOrder) {
        ResponseMessage result = new ResponseMessage();
        String vCode = request.getParameter("code");

        String sCode = (String) request.getSession().getAttribute(VerifyCode.VERIFY_TYPE_COMMENT_FRONT_INQUIRY);

        if (StringUtils.isBlank(vCode) || null == sCode || (!vCode.equalsIgnoreCase(sCode))) {
            result.setIsSuccess(false);
            result.setCode(MessageCode.VERIFYCODE_EMPTY.getCode());
            result.setMessage(MessageCode.VERIFYCODE_EMPTY.getMessage());
            return result;
        }

        try {
            onlineOrder.setIsLink(0);
            onlineOrderService.insert(onlineOrder);
            result.setIsSuccess(true);
            result.setMsgCode(MessageCode.DEFAULT_SUCCESS);


        } catch (Exception e) {
            e.printStackTrace();
            result.setIsSuccess(false);
            result.setMsgCode(MessageCode.DEFAULT_FAILED);

        }
        return result;
    }
}
