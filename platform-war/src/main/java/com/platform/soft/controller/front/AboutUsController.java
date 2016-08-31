package com.platform.soft.controller.front;

import com.google.common.collect.Maps;
import com.platform.soft.api.backstage.ICommonService;
import com.platform.soft.api.backstage.INavigationService;
import com.platform.soft.domain.backstage.Common;
import com.platform.soft.domain.backstage.DynamicOrder;
import com.platform.soft.domain.backstage.Order;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
public class AboutUsController {

    @Autowired
    private INavigationService navigationService;
    @Autowired
    private ICommonService commonService;


    @RequestMapping(value = "/about_us", method = RequestMethod.GET)
    public String CargoTrace(HttpServletRequest request) {
        getNavigation(request);
        return "html/about_us";
    }

    private void getNavigation(HttpServletRequest request) {
        //导航
        Map<String, Object> map = Maps.newHashMap();
        map.put("status", "1");
        request.setAttribute("navigation", navigationService.queryList(map, "id", "asc"));
        request.setAttribute("select", 4);
        //关于我们
        Common common= (Common) commonService.queryOne(new HashMap<String, Object>());
        request.setAttribute("common",common);

    }
}
