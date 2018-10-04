package com.platform.soft.controller;

import com.alibaba.fastjson.JSON;
import com.platform.soft.api.LinksService;
import com.platform.soft.domain.dto.LinksDTO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("service")
public class ShortUrlController {
    private final static Logger logger = LoggerFactory.getLogger(ShortUrlController.class);

    @Autowired
    LinksService linksService;

    @RequestMapping("/getShortUrl")
    @ResponseBody
    public LinksDTO getShortUrl(String longUrl) {

        LinksDTO linksDTO = new LinksDTO();
        if (StringUtils.isBlank(longUrl)) {
            linksDTO.setErrMsg("源链接不能为空");
            return linksDTO;
        }
        logger.info("start getShortUrl longUrl:{}", longUrl);
        try {
            linksDTO = linksService.getShortUrl(longUrl);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            linksDTO.setErrMsg("系统异常，请稍后重试");
        }
        logger.info("end getShortUrl linksDTO:{}", JSON.toJSONString(linksDTO));

        return linksDTO;
    }
}
