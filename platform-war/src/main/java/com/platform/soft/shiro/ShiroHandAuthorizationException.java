package com.platform.soft.shiro;

import com.platform.soft.utils.WebUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(ShiroHandAuthorizationException.class);

    /**
     * Called by the web container to indicate to a filter that it is
     * being placed into service.
     * <p>
     * <p>The servlet container calls the init
     * method exactly once after instantiating the filter. The init
     * method must complete successfully before the filter is asked to do any
     * filtering work.
     * <p>
     * <p>The web container cannot place the filter into service if the init
     * method either
     * <ol>
     * <li>Throws a ServletException
     * <li>Does not return within a time period defined by the web container
     * </ol>
     *
     * @param filterConfig
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {


        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        Subject subject = SecurityUtils.getSubject();
        if (subject.getPrincipal() == null && req.getServletPath().contains("/backstage/")) {
            if (WebUtils.isAjax(req)) {

                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<div style=\"text-align: center\"><br><br>您的登录状态以失效<br><br>" +
                        "请点击<a href='" + WebUtils.getRootPath(req) + "logout.do'>登录</a>重新登录<br><br></div>");
                out.flush();
                out.close();
                return;
            }
        }

        try {
            chain.doFilter(request, response);
        } catch (Exception e) {
            Throwable rootCause = e;
            while (rootCause.getCause() != null) {
                rootCause = rootCause.getCause();
            }

            if (rootCause instanceof AuthorizationException) {
                if (WebUtils.isAjax(req)) {
                    response.setCharacterEncoding("UTF-8");
                    PrintWriter out = response.getWriter();
                    out.println("<div style=\"text-align: center\"><br><br>您的请求被拒绝,您没有足够的权限<br>" +
                            "请<a href='" + WebUtils.getRootPath(req) + "logout.do'>登录</a>更换用户后重试<br><br></div>");
                    out.flush();
                    out.close();
                } else {
                    request.getRequestDispatcher(WebUtils.getRootPath(req) + "403.jsp").forward(
                            request, response);
                    return;
                }
            } else {
                if (req.getServletPath().contains("/api/")) {
                    resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                    return;
                } else {
                    LOGGER.error("您没有权限访问,原因:{}", e.getMessage());
                    throw new ServletException(e.getMessage());
                }
            }
        }
    }

    /**
     * Called by the web container to indicate to a filter that it is being
     * taken out of service.
     * <p>
     * <p>This method is only called once all threads within the filter's
     * doFilter method have exited or after a timeout period has passed.
     * After the web container calls this method, it will not call the
     * doFilter method again on this instance of the filter.
     * <p>
     * <p>This method gives the filter an opportunity to clean up any
     * resources that are being held (for example, memory, file handles,
     * threads) and make sure that any persistent state is synchronized
     * with the filter's current state in memory.
     */
    @Override
    public void destroy() {

    }


}
