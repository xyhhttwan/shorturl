package com.platform.soft.controller.backstage.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 框架跳转
 *
 * @author baixb
 * @version [v1.0，2015/6/17]
 */
@Controller
@RequestMapping("/backstage")
public class FrameController {
    @RequestMapping("/main")
    public ModelAndView main() {
        return new ModelAndView("frame/main");
    }
}
