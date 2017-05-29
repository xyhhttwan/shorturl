package com.platform.soft.controller.wx;

import com.platform.soft.api.backstage.IOnlineOrderService;
import com.platform.soft.base.domain.ResponseMessage;
import com.platform.soft.base.domain.VerifyCode;
import com.platform.soft.base.enums.MessageCode;
import com.platform.soft.base.springmvc.BackStageController;
import com.platform.soft.domain.backstage.OnlineOrder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 在线下单
 */
@Controller
@RequestMapping("/html/wx/online_order/")
public class OnlineOrderWxController extends BackStageController {


    private static final Logger LOGGER = LoggerFactory.getLogger(OnlineOrderWxController.class);

    @Autowired
    private IOnlineOrderService onlineOrderService;

    //微信页面跳转
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "html/wx/online_order";
    }

    @ResponseBody
    @RequestMapping(value = "/add_online_order", method = RequestMethod.POST)
    public ResponseMessage AddOnLineOrder(HttpServletRequest request, OnlineOrder onlineOrder) {

        LOGGER.debug("开始新增在线订单:{}", onlineOrder);

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
            LOGGER.error(e.getMessage(), e);
            result.setIsSuccess(false);
            result.setMsgCode(MessageCode.DEFAULT_FAILED);

        }
        LOGGER.debug("新增在线订单结束");

        return result;
    }

}
