package com.platform.soft.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 1.自定义角色鉴权过滤器(满足其中一个角色则认证通过)
 * 2.扩展异步请求认证提示功能;
 *
 */
public class RolePermissionAuthorizationFilter extends PermissionsAuthorizationFilter {
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        Subject subject = getSubject(request, response);
        if (subject.getPrincipal() == null) {
            if (com.platform.soft.utils.WebUtils.isAjax(httpRequest)) {

                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();
                out.println("您尚未登录或登录时间过长,请重新<a href='/login.jsp'>登录</a>!");
                out.flush();
                out.close();
                return false;
            } else {
                saveRequestAndRedirectToLogin(request, response);
            }
        } else {
            if (com.platform.soft.utils.WebUtils.isAjax(httpRequest)) {
                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();
                out.println("您没有足够的权限执行该操作!");
                out.flush();
                out.close();
            } else {
                String unauthorizedUrl = getUnauthorizedUrl();
                if ( org.apache.shiro.util.StringUtils.hasText(unauthorizedUrl)) {
                    WebUtils.issueRedirect(request, response, unauthorizedUrl);
                } else {
                    WebUtils.toHttp(response).sendError(401);
                }
            }
        }
        return false;
    }



}
