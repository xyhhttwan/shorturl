package com.platform.soft.controller.backstage;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.platform.soft.api.backstage.INewsService;
import com.platform.soft.base.domain.ResponseMessage;
import com.platform.soft.base.enums.MessageCode;
import com.platform.soft.base.springmvc.BackStageController;
import com.platform.soft.common.utils.Status;
import com.platform.soft.domain.backstage.News;
import com.platform.soft.domain.backstage.NewsTitle;
import com.platform.soft.domain.backstage.Order;
import org.apache.commons.lang3.StringUtils;
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
 * Created by baixiaobin on 16/8/11.
 */
@Controller
@Scope("prototype")
@RequestMapping("/backstage/news/newsManage")
public class NewsController extends BackStageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NewsController.class);

    @Autowired
    private INewsService newsService;


    @RequestMapping("/index")
    public String index() {
        return "news/list";
    }


    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(@RequestParam(value = "status",defaultValue ="") String status) {
        initPageRows();
        PageHelper.startPage(page, rows);
        Map<String, Object> map = new HashMap<String, Object>();
        if(StringUtils.isNotBlank(status)){
            map.put("status", status);
        }
        List<News> newsList = newsService.queryList(map, "id", "asc");
        PageInfo<List<News>> pageList = new PageInfo(newsList);
        map.clear();
        map.put("total", pageList.getTotal());
        map.put("rows", pageList.getList());
        return map;
    }

    /**
     * 新增跳转
     *
     * @return String
     */
    @RequestMapping("/addView")
    @RequiresPermissions("news-add")
    public String addView() {

        return "news/add";
    }


    /**
     * 新增
     *
     * @return String
     */
    @RequestMapping("/add")
    @ResponseBody
    @RequiresPermissions("news-add")
    public ResponseMessage add(News news) {
        ResponseMessage rsgMsg = new ResponseMessage();
        try {
            news.setCreateDate(System.currentTimeMillis());
            news.setPostTime(new Date());
            news.setUpdateTime(new Date());
            news.setStatus(Status.NORMAL.getIndex());
            if(news.getNewsType()==1){
                news.setPic("");
            }
            newsService.insert(news);
            rsgMsg.setIsSuccess(true);
            rsgMsg.setMsgCode(MessageCode.DEFAULT_SUCCESS);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            rsgMsg.setIsSuccess(false);
            rsgMsg.setMsgCode(MessageCode.ADD_FAILED);
        }
        return rsgMsg;
    }


    /**
     * 修改跳转
     *
     * @return
     */

    @RequestMapping("/updateView")
    @RequiresPermissions("news-update")
    public String updateView(Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id",id);
        News news = (News) newsService.queryOne(map);

        request.setAttribute("news", news);
        return "news/add";
    }

    @RequestMapping("/update")
    @ResponseBody
    @RequiresPermissions("news-update")
    public ResponseMessage update(News news) {
        ResponseMessage rsgMsg = new ResponseMessage();
        news.setUpdateTime(new Date());
        if(news.getNewsType()==1){
            news.setPic("");
        }

        news.setLastModDate(System.currentTimeMillis());
        try {
            newsService.update(news);
            rsgMsg.setIsSuccess(true);
            rsgMsg.setMsgCode(MessageCode.DEFAULT_SUCCESS);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            rsgMsg.setIsSuccess(false);
            rsgMsg.setMsgCode(MessageCode.UPDATE_FAILED);
        }
        return rsgMsg;
    }


    @RequestMapping("/delete")
    @ResponseBody
    @RequiresPermissions("news-delete")
    public ResponseMessage delete(String ids) {
        ResponseMessage rsgMsg = new ResponseMessage();
        try {
            newsService.deleteById(ids);
            rsgMsg.setIsSuccess(true);
            rsgMsg.setMsgCode(MessageCode.DEFAULT_SUCCESS);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            rsgMsg.setIsSuccess(false);
            rsgMsg.setMsgCode(MessageCode.DELETE_FAILED);
        }
        return rsgMsg;
    }

    @RequestMapping("/setPublishOrUnPublish")
    @ResponseBody
    @RequiresPermissions("newsTitle-publish")
    public ResponseMessage setPublishOrUnPublish(String ids,String status) {
        ResponseMessage rsgMsg = new ResponseMessage();

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("id",ids);
        map.put("status",status);
        try {
            newsService.updateMap(map);
            rsgMsg.setIsSuccess(true);
            rsgMsg.setMsgCode(MessageCode.DEFAULT_SUCCESS);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            rsgMsg.setIsSuccess(false);
            rsgMsg.setMsgCode(MessageCode.DELETE_FAILED);
        }
        return rsgMsg;
    }




}
