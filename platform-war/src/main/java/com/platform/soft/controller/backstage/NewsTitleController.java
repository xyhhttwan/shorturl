package com.platform.soft.controller.backstage;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.platform.soft.api.backstage.INewsTitleService;
import com.platform.soft.base.domain.ResponseMessage;
import com.platform.soft.base.enums.MessageCode;
import com.platform.soft.base.springmvc.BackStageController;
import com.platform.soft.common.utils.Status;
import com.platform.soft.domain.backstage.News;
import com.platform.soft.domain.backstage.NewsTitle;
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
 * Created by baixiaobin on 16/8/17.
 */
@Controller
@Scope("prototype")
@RequestMapping("/backstage/news/newsTitleManage")
public class NewsTitleController extends BackStageController {


    private static final Logger LOGGER = LoggerFactory.getLogger(NewsTitleController.class);

    @Autowired
    private INewsTitleService newsTitleService;



    @RequestMapping("/index")
    public String index() {
        return "newsTitle/list";
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
        List<News> newsList = newsTitleService.queryList(map, "id", "asc");
        PageInfo<List<NewsTitle>> pageList = new PageInfo(newsList);
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
    @RequiresPermissions("newsTitle-add")
    public String addView() {

        return "newsTitle/add";
    }


    /**
     * 新增
     *
     * @return String
     */
    @RequestMapping("/add")
    @ResponseBody
    @RequiresPermissions("newsTitle-add")
    public ResponseMessage add(NewsTitle newsTitle) {
        ResponseMessage rsgMsg = new ResponseMessage();
        try {
            newsTitle.setPostTime(new Date());
            newsTitle.setUpdateTime(new Date());

            newsTitle.setStatus(Status.NORMAL.getIndex());
            newsTitleService.insert(newsTitle);
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
    @RequiresPermissions("newsTitle-update")
    public String updateView(Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        NewsTitle newsTitle = (NewsTitle) newsTitleService.queryOne(map);
        map.put("id", id);
        request.setAttribute("newsTitle", newsTitle);
        return "newsTitle/add";
    }

    @RequestMapping("/update")
    @ResponseBody
    @RequiresPermissions("newsTitle-update")
    public ResponseMessage update(NewsTitle news) {
        ResponseMessage rsgMsg = new ResponseMessage();
        news.setUpdateTime(new Date());
        try {
            newsTitleService.update(news);
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
    @RequiresPermissions("newsTitle-delete")
    public ResponseMessage delete(String ids) {
        ResponseMessage rsgMsg = new ResponseMessage();
        try {
            newsTitleService.deleteById(ids);
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
            newsTitleService.updateMap(map);
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
