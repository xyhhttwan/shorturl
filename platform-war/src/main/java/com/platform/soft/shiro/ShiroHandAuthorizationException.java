package com.platform.soft.shiro;

import com.platform.soft.utils.WebUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * shiro 权限验证异常处理
 */
@Controller
public class ShiroHandAuthorizationException implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws  IOException, ServletException {

         try {
              chain.doFilter(request, response);
            } catch (ServletException e) {
            Throwable rootCause = e;
            while (rootCause.getCause() != null) {
                rootCause  = rootCause.getCause();
            }
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;

            if(rootCause instanceof AuthorizationException){
                if (WebUtils.isAjax(req)) {
                    response.setCharacterEncoding("UTF-8");
                    PrintWriter out = response.getWriter();
                    out.println("<div style=\"text-align: center\"><br><br>您的请求被拒绝,您没有足够的权限<br>" +
                            "请<a href='"+ WebUtils.getRootPath(req)+"logout.do'>登录</a>更换用户后重试<br><br></div>");
                    out.flush();
                    out.close();
                } else {
                    request.getRequestDispatcher(WebUtils.getRootPath(req)+"403.jsp").forward(
                            request, response);
                    return;
                }
            }else{
                if(req.getServletPath().contains("/api/")){
                    resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                    return ;
                }else{
                    throw new ServletException(e.getMessage());
                }
            }
         }
    }

    @Override
    public void destroy() {

    }
}
