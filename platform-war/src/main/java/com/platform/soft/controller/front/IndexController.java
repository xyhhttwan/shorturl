package com.platform.soft.controller.front;

import com.google.common.collect.Maps;
import com.platform.soft.api.backstage.INavigationService;
import com.platform.soft.base.springmvc.BackStageController;
import com.platform.soft.domain.backstage.Navigation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * Created by baixiaobin on 16/8/15.
 */
@Controller
@RequestMapping("/html/")
public class IndexController extends BackStageController{

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private INavigationService navigationService;

    @RequestMapping("index")
    public String list(){
        //导航
        Map<String,Object> map = Maps.newHashMap();
        map.put("status","1");
        request.setAttribute("navigation",navigationService.queryList(map,"id","asc"));

        //新闻


        return "html/index";
    }
}
