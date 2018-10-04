package com.platform.soft.controller;

import com.platform.soft.api.LinksService;
import com.platform.soft.domain.Links;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ForwardController {

    private final static Logger logger = LoggerFactory.getLogger(ForwardController.class);

    @Autowired
    LinksService linksService;

    @RequestMapping(value = "{key}", method = RequestMethod.GET)
    public void redirect(@PathVariable(value = "key") String key,
                         HttpServletRequest request, HttpServletResponse httpServletResponse) {

        logger.info("短链接转换开始:{}", request.getRequestURI());
        try {
            Links shortUrl = findShortUrl(request, key);
            if (shortUrl != null) {
                redirect(httpServletResponse, shortUrl);
            } else {
                httpServletResponse.sendRedirect("http://link.coolcoder.cc");
            }
        } catch (IOException e) {
            try {
                httpServletResponse.sendRedirect("/");
            } catch (IOException e1) {
                //
            }

        }

    }

    private Links findShortUrl(HttpServletRequest request, String key) {
        return linksService.findByKey(key);
    }

    private void redirect(HttpServletResponse httpServletResponse, Links shortUrl) throws IOException {
        String destinationUrl = shortUrl.getUrl();
        // destinationUrl = URLEncoder.encode(destinationUrl, CharEncoding.UTF_8);
        httpServletResponse.sendRedirect(destinationUrl);
    }
}
