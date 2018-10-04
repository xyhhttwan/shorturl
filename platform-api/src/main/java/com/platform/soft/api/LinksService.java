package com.platform.soft.api;

import com.platform.soft.domain.Links;
import com.platform.soft.domain.dto.LinksDTO;

public interface LinksService {

    /**
     * 根据短链回去长连接
     *
     * @param longUrl
     * @return LinksDTO
     */
    LinksDTO getShortUrl(String longUrl);


    Links findByKey(String key);
}
