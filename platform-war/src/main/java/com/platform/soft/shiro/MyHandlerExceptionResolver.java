package com.platform.soft.shiro;

import com.platform.soft.utils.WebUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2015/6/22.
 */
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception exception) {
        //是否为ajax请求
        String requestType = request.getHeader("X-Requested-With");
        if(exception instanceof AuthorizationException){
            response.setStatus(413);//无权限异常  主要用于ajax请求返回
            response.addHeader("Error-Json", "{code:413,msg:'nopermission',script:''}");
            response.setContentType("text/html;charset=utf-8");
            if("XMLHttpRequest".equals(requestType)){
                return new ModelAndView();
            }
            return new ModelAndView("redirect:"+ WebUtils.getRootPath(request)+"html/413.html");
        }
        return null;
    }
}
