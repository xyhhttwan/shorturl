package com.platform.soft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("html")
public class indexController {

    @RequestMapping("index")
    public String index() {
        return "/html/index";
    }

    @RequestMapping("api")
    public String api() {
        return "/html/api";
    }
}
