package com.platform.soft.service.service;

import com.platform.soft.api.IRedisRepository;
import com.platform.soft.api.LinksService;
import com.platform.soft.common.constans.Constans;
import com.platform.soft.common.utils.Encode64;
import com.platform.soft.domain.IdBuilder;
import com.platform.soft.domain.Links;
import com.platform.soft.domain.dto.LinksDTO;
import com.platform.soft.service.dao.LinksDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author baixiaobin
 */
@Service
public class LinksServiceImpl implements LinksService {

    @Autowired
    private LinksDAO linksDAO;

    @Autowired
    private IRedisRepository<String, Object> redisRepository;

    private final static Logger logger = LoggerFactory.getLogger(LinksServiceImpl.class);
    @Autowired
    private TaskExecutor executor;


    /**
     * 根据短链回去长连接
     *
     * @param longUrl
     * @return LinksDTO
     */
    @Override
    public LinksDTO getShortUrl(String longUrl) {
        LinksDTO linksDTO = new LinksDTO();
        linksDTO.setLonguUrl(longUrl);
        linksDTO.setResult(true);
        String shortUrl = getCache(longUrl);
        if (null != shortUrl) {
            linksDTO.setShortUrl(shortUrl);
            return linksDTO;
        }
        String key = generatorKey(getMaxId());
        shortUrl = buildShortUrl(key);
        linksDTO.setShortUrl(shortUrl);
        String finalShortUrl = shortUrl;

        executor.execute(() -> {
            addLinks(key, longUrl, linksDTO.getShortUrl());
            addCache(finalShortUrl, longUrl);
        });

        return linksDTO;
    }

    private String getCache(String longUrl) {
        try {
            Object shortUrl = redisRepository.get(longUrl);
            if (null != shortUrl) {
                redisRepository.expire(longUrl, 12, TimeUnit.HOURS);
                return shortUrl.toString();
            }
        } catch (Exception e) {
            logger.error("获取redis短链失败,msg:{} e:{}", e.getMessage(), e);
        }
        return null;
    }

    private void addCache(String shortUrl, String longUrl) {
        try {
            redisRepository.set(longUrl, shortUrl, 24, TimeUnit.HOURS);
        } catch (Exception e) {
            logger.error("缓存短链失败,msg:{} e:{}", e.getMessage(), e);
        }
    }

    @Override
    public Links findByKey(String key) {
        return linksDAO.findByKey(key);
    }

    private void addLinks(String key, String longUrl, String shortUrl) {
        Links links = new Links();
        Long now = System.currentTimeMillis();
        links.setCreateTime(now);
        links.setUpdateTime(now);
        links.setType(1);
        links.setKey(key);
        links.setUrl(longUrl);
        links.setShortUrl(shortUrl);
        linksDAO.addLinks(links);

    }

    private String buildShortUrl(String key) {

        return Constans.URL_PREFIX + key;
    }

    public Long getMaxId() {
        IdBuilder builder = new IdBuilder();
        builder.setBiz(1);
        builder.setStep(1);
        linksDAO.updateId(builder);
        return builder.getId();
    }

    private String generatorKey(Long maxId) {
        logger.info("获取到数据主键id:{}", maxId);
        String key = Encode64.CompressNumber(maxId);
        logger.info("数据主键id:{},生成对应的key:{}", maxId, key);
        return key;
    }

    public static void main(String[] args) {
        System.out.println(new LinksServiceImpl().generatorKey(10000082L));
    }
}
