package com.platform.soft.controller.wx;

import com.platform.soft.api.backstage.ICommonService;
import com.platform.soft.base.springmvc.BackStageController;
import com.platform.soft.domain.backstage.Common;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;

/**
 * Created by baixiaobin on 16/9/5.
 */
@Controller
@RequestMapping("/html/wx/about/")
public class AboutWxController extends BackStageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AboutWxController.class);

    @Autowired
    private ICommonService commonService;

    //微信页面跳转
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        //关于我们
        Common common= (Common) commonService.queryOne(new HashMap<String, Object>());
        request.setAttribute("common",common);
        return "html/wx/about";
    }

}
