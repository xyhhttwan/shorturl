package com.platform.soft.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by baixiaobin on 16/8/15.
 */
public class PermissionFilter extends PermissionsAuthorizationFilter {
//    /**
//     * Returns <code>true</code> if the request is allowed to proceed through the filter normally, or <code>false</code>
//     * if the request should be handled by the
//     * {@link #onAccessDenied(ServletRequest, ServletResponse, Object) onAccessDenied(request,response,mappedValue)}
//     * method instead.
//     *
//     * @param request     the incoming <code>ServletRequest</code>
//     * @param response    the outgoing <code>ServletResponse</code>
//     * @param mappedValue the filter-specific config value mapped to this filter in the URL rules mappings.
//     * @return <code>true</code> if the request should proceed through the filter normally, <code>false</code> if the
//     * request should be processed by this filter's
//     * {@link #onAccessDenied(ServletRequest, ServletResponse, Object)} method instead.
//     * @throws Exception if an error occurs during processing.
//     */
//    @Override
//    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
//
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse res = (HttpServletResponse) response;
//
//
//        Subject subject = getSubject(request, response);
//        // If the subject isn't identified, redirect to login URL
//        if (subject.getPrincipal() == null) {
//            if (com.platform.soft.utils.WebUtils.isAjax(req)) {
//                response.setCharacterEncoding("UTF-8");
//                PrintWriter out = null;
//                try {
//                    out = response.getWriter();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                out.println("<div style=\"text-align: center\"><br><br>您的登录状态以失效<br><br>" +
//                        "请点击<a href='" + com.platform.soft.utils.WebUtils.getRootPath(req) + "logout.do'>登录</a>重新登录<br><br></div>");
//                out.flush();
//                out.close();
//                return true;
//            } else {
//                saveRequestAndRedirectToLogin(request, response);
//               // res.sendRedirect(com.platform.soft.utils.WebUtils.getRootPath(req) + "index.jsp");
//                // req.getRequestDispatcher(com.platform.soft.utils.WebUtils.getRootPath(req) + "index.jsp").forward(req,res);
////                request.getRequestDispatcher(com.platform.soft.utils.WebUtils.getRootPath(req) + "index.jsp").forward(
////                            request, response);
//                return true;
//
//            }
//        } else {
//
//            String unauthorizedUrl = getUnauthorizedUrl();
//            //SHIRO-142 - ensure that redirect _or_ error code occurs - both cannot happen due to response commit:
//
//            if (StringUtils.hasText(unauthorizedUrl)) {
//                WebUtils.issueRedirect(request, response, unauthorizedUrl);
//            } else {
//                WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
//            }
//            return false;
//
////            if (com.platform.soft.utils.WebUtils.isAjax(req)) {
////                response.setCharacterEncoding("UTF-8");
////                PrintWriter out = response.getWriter();
////                out.println("<div style=\"text-align: center\"><br><br>您的请求被拒绝,您没有足够的权限<br>" +
////                        "请<a href='" + com.platform.soft.utils.WebUtils.getRootPath(req) + "logout.do'>登录</a>更换用户后重试<br><br></div>");
////                out.flush();
////                out.close();
////                return false;
////            } else {
////                // If subject is known but not authorized, redirect to the unauthorized URL if there is one
////                // If no unauthorized URL is specified, just return an unauthorized HTTP status code
////
////                return true;
////            }
//
//
//        }
//    }
//


    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {


        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        Subject subject = getSubject(request, response);


        if (subject.getPrincipal() == null) {
            if (com.platform.soft.utils.WebUtils.isAjax(req)) {
                response.setCharacterEncoding("UTF-8");
                PrintWriter out = null;
                out = response.getWriter();
                out.println("<div style=\"text-align: center\"><br><br>您的登录状态以失效<br><br>" +
                        "请点击<a href='" + com.platform.soft.utils.WebUtils.getRootPath(req) + "logout.do'>登录</a>重新登录<br><br></div>");
                out.flush();
                out.close();
                return false;
            } else {
                saveRequestAndRedirectToLogin(request, response);
                // res.sendRedirect(com.platform.soft.utils.WebUtils.getRootPath(req) + "index.jsp");
                // req.getRequestDispatcher(com.platform.soft.utils.WebUtils.getRootPath(req) + "index.jsp").forward(req,res);
//                request.getRequestDispatcher(com.platform.soft.utils.WebUtils.getRootPath(req) + "index.jsp").forward(
//                            request, response);
                return false;

            }
        }

        String[] perms = (String[]) mappedValue;

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        boolean isPermitted = true;
        if (perms != null && perms.length > 0) {
            if (perms.length == 1) {
                if (!subject.isPermitted(perms[0])) {
                    isPermitted = false;
                }
            } else {
                if (!subject.isPermittedAll(perms)) {
                    isPermitted = false;
                }
            }
        }
        if (!isPermitted) {
            if (com.platform.soft.utils.WebUtils.isAjax(httpRequest)) {
                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<div style=\"text-align: center\"><br><br>您的请求被拒绝,您没有足够的权限<br>" +
                        "请<a href='" + com.platform.soft.utils.WebUtils.getRootPath(httpRequest) + "logout.do'>登录</a>更换用户后重试<br><br></div>");
                out.flush();
                out.close();
                return true;
            } else {
                try {
                    request.getRequestDispatcher(com.platform.soft.utils.WebUtils.getRootPath(httpRequest) + "403.jsp").forward(
                            request, response);
                } catch (ServletException e) {
                    e.printStackTrace();
                }
                return true;
            }
        }
        return isPermitted;
    }


}
