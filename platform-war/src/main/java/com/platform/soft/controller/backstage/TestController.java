package com.platform.soft.controller.backstage;

import com.platform.soft.controller.api.Users;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by baixiaobin on 16/8/4.
 */
@Controller
public class TestController {


    @ResponseBody
    @RequestMapping("/getMap")
    public Users get(){

        return new Users();
    }
}
