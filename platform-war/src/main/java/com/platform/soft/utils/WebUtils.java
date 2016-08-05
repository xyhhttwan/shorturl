package com.platform.soft.utils;



import com.platform.soft.domain.backstage.ex.SystemUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * web 相关的工具类
 *
 * @author baixb
 * @version [v1.0，2015/6/24]
 */

public class WebUtils {

    @Resource static private HttpServletRequest request;

    private static final Logger LOGGER = LoggerFactory.getLogger(WebUtils.class);

    public static String getUserName(HttpServletRequest request) {

        try {
            SystemUser systemUser = (SystemUser) request.getSession().getAttribute("currentUser");
            if (null != systemUser) {
                return systemUser.getSystemName();
            }
        } catch (Exception e) {
            LOGGER.error("获取SystemUser失败:" + e.getMessage());
        }
        return "";

    }

    public static int getUserId(HttpServletRequest request) {

        try {
            SystemUser systemUser = (SystemUser) request.getSession().getAttribute("currentUser");
            if (null != systemUser) {
                return systemUser.getId();
            }
        } catch (Exception e) {
            LOGGER.error("获取SystemUser失败:" + e.getMessage());
        }
        return -1;

    }





    /**
     * 获取真实的ip
     *
     * @param request
     * @return
     */
    public String getIp(HttpServletRequest request) {

        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();

        }
        return ip;
    }


    public static  boolean isMatchUrl(String url ){
        String regex = "^(http?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]" ;
        Pattern patt = Pattern. compile(regex );
        Matcher matcher = patt.matcher(url);
        boolean isMatch = matcher.matches();
        if (!isMatch) {
          return false;
        } else {
            return true;
        }
    }

}
