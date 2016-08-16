package com.platform.soft.controller.backstage;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.platform.soft.api.backstage.ICommonService;
import com.platform.soft.api.backstage.IOrderService;
import com.platform.soft.base.domain.ResponseMessage;
import com.platform.soft.base.enums.MessageCode;
import com.platform.soft.base.springmvc.BackStageController;
import com.platform.soft.common.domain.SearchField;
import com.platform.soft.common.utils.Status;
import com.platform.soft.domain.backstage.Common;
import com.platform.soft.domain.backstage.Order;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by baixiaobin on 16/8/14.
 */
@Controller
@Scope("prototype")
@RequestMapping("/backstage/common/commonManage")
public class PublishController extends BackStageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private ICommonService commonService;


    @RequestMapping("/index")
    public String index() {
        Common common = (Common) commonService.queryOne(new HashMap<String, Object>());
        request.setAttribute("common",common);
        return "comm/add";
    }


    @RequestMapping("/update")
    @ResponseBody
    public ResponseMessage update(Common common) {
        ResponseMessage rsgMsg = new ResponseMessage();
        try {
            commonService.updateOrSave(common,common.getId());
            rsgMsg.setIsSuccess(true);
            rsgMsg.setMsgCode(MessageCode.DEFAULT_SUCCESS);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            rsgMsg.setIsSuccess(false);
            rsgMsg.setMsgCode(MessageCode.UPDATE_FAILED);
        }
        return rsgMsg;
    }

}
