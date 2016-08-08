package com.platform.soft.controller.api;

import com.platform.soft.api.backstage.IUserService;
import com.platform.soft.controller.backstage.system.MenuController;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by baixiaobin on 16/8/4.
 */
@Controller
@RequestMapping("/api")
@Api(value = "地址管理",description = "地址管理",tags = "platform云服务")
public class MainController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private IUserService userService;

    @ResponseBody
    @RequestMapping("/v1/getList")
    @ApiOperation(value = "获取省份", notes = "获取省份", httpMethod = "GET",response=Users.class)
    public List<Users> getList(){
        LOGGER.debug("开始获取省份信息");
        return userService.findAll();
    }


    @ResponseBody
    @RequestMapping("/update")
    @ApiOperation(value = "修改省份", notes = "修改省份", httpMethod = "POST",response=Users.class)
    public Users update(){
        return new Users();
    }

}
