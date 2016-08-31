package com.platform.soft.controller.front;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.platform.soft.api.backstage.INavigationService;
import com.platform.soft.api.backstage.INewsService;
import com.platform.soft.base.springmvc.BackStageController;
import com.platform.soft.domain.backstage.News;
import com.platform.soft.utils.HtmlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by baixiaobin on 16/8/29.
 */
@Controller
@RequestMapping("/html")
public class NewsListController  extends BackStageController {

    @Autowired
    private INewsService newsService;

    @Autowired
    private INavigationService navigationService;

    @RequestMapping("news/{id}")
    public String newsDetail(@PathVariable("id") int id){
        getNavigation(request);
        Map<String,Object> map = Maps.newHashMap();
        map.put("id",id);
        map.put("status",1);
        News news = (News) newsService.queryOne(map);
        request.setAttribute("news",news);
        return "html/newsDetail";
    }

    @RequestMapping(value = "news_list",method = RequestMethod.GET )
    public String newsLis(){
        getNavigation(request);
//        Map<String,Object> map = Maps.newHashMap();
//        initPageRows();
//        PageHelper.startPage(page,rows);
//        map.put("status",1);
//        List<News> newsList= newsService.queryList(map,"updateTime","desc");
//        for(News news :newsList){
//            news.setContent(HtmlUtils.getTextFromHtml(news.getContent()));
//        }
//        PageInfo<List<News>> pageInfo = new PageInfo(newsList);
//        request.setAttribute("page",pageInfo);
        return "html/news_list";
    }

    @RequestMapping(value = "getNewsDataList",method = RequestMethod.GET )
    public String getNewsDataList(
            @RequestParam(value = "rows",required = false,defaultValue = "4") int rows,
            @RequestParam(value = "pno",required = false,defaultValue = "1")int page
    ){
        Map<String,Object> map = Maps.newHashMap();
        initPageRows();
        PageHelper.startPage(page,rows);
        map.put("status",1);
        List<News> newsList= newsService.queryList(map,"updateTime","desc");
        for(News news :newsList){
            news.setContent(HtmlUtils.getTextFromHtml(news.getContent()));

        }
        PageInfo<List<News>> pageInfo = new PageInfo(newsList);
        request.setAttribute("page",pageInfo);
        return "html/ajax/news_list";
    }

    private void getNavigation(HttpServletRequest request) {
        //导航
        Map<String, Object> map = Maps.newHashMap();
        map.put("status", "1");
        request.setAttribute("navigation", navigationService.queryList(map, "id", "asc"));
        request.setAttribute("select", 3);
    }
}
