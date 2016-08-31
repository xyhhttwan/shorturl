package com.platform.soft.controller.front;

import com.google.common.collect.Maps;
import com.platform.soft.api.backstage.ICommonService;
import com.platform.soft.api.backstage.INavigationService;
import com.platform.soft.api.backstage.INewsService;
import com.platform.soft.api.backstage.INewsTitleService;
import com.platform.soft.base.springmvc.BackStageController;
import com.platform.soft.common.domain.SearchField;
import com.platform.soft.common.utils.SqlOrderEnum;
import com.platform.soft.common.utils.Status;
import com.platform.soft.domain.backstage.Common;
import com.platform.soft.domain.backstage.Navigation;
import com.platform.soft.domain.backstage.News;
import com.platform.soft.domain.backstage.NewsTitle;
import com.platform.soft.utils.HtmlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
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

    @Autowired
    private INewsTitleService newsTitleService;

    @Autowired
    private INewsService newsService;

    @Autowired
    private ICommonService commonService;

    @RequestMapping("index")
    public String list(){
        //导航
        Map<String,Object> map = Maps.newHashMap();
        map.put("status","1");
        request.setAttribute("navigation",navigationService.queryList(map,"id","asc"));
        request.setAttribute("select",0);

        //新闻大图

        map.put("groupOp", "and");
        map.put("status",new SearchField("status", "=", 1));

        List<NewsTitle> listTitleNews = newsTitleService.queryPage(map,0,3,"updateTime", SqlOrderEnum.DESC);
        request.setAttribute("newTitle",listTitleNews);

        map.put("status",1);
        News newsPic =(News)newsService.queryOne(map,"updateTime", SqlOrderEnum.DESC);
        newsPic.setContent(HtmlUtils.getTextFromHtml(newsPic.getContent()));
        request.setAttribute("newsPic",newsPic);

        //公共信息
        //关于我们
        Common common= (Common) commonService.queryOne(new HashMap<String, Object>());
        request.setAttribute("common",common);

        return "html/index";
    }
}
