package com.platform.soft.controller;

import com.platform.soft.api.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by baixiaobin on 16/8/4.
 */
@Controller
public class MainController {

    @Autowired
    private IUserService userService;

    @ResponseBody
    @RequestMapping("/add")
    public List add(){
        return userService.findAll();
    }


}
