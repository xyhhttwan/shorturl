/**
 * yingyinglicai.com Inc.
 * Copyright (c) 2013-2013 All Rights Reserved.
 */
package com.platform.soft.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.RequestContextFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 性能摘要日志
 * <p/>
 * <p/>
 * 创建时间: 2014年11月24日 下午9:22:39 <br/>
 *
 * @author gfzhao
 * @since v0.0.1
 */
public class RequestDigestFilter extends RequestContextFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger("CICADA-DIGEST");

	/**
	 * @see RequestContextFilter#doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
	                                FilterChain filterChain) throws ServletException, IOException {

		boolean hasError = false;
		StringBuffer serviceMessage = new StringBuffer();
		long startTime = System.currentTimeMillis();
		try {
			serviceMessage.append("(").append(request.getRequestURI()).append(",")
					.append(request.getParameter("token")).append(",").append(request.getMethod())
					.append(",");

			filterChain.doFilter(request, response);
		} catch (Exception e) {
			logger.error("请求错误,{}",e);
			hasError = true;
		} finally {
			if (LOGGER.isInfoEnabled()) {
				if (hasError) {
					serviceMessage.append("N");
				} else {
					serviceMessage.append("Y");
				}
				long spaceTime = System.currentTimeMillis() - startTime;
				serviceMessage.append(",").append(spaceTime).append("ms").append(")");
				LOGGER.info(serviceMessage.toString());
			}

		}
	}

}
