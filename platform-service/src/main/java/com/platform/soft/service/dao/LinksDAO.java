package com.platform.soft.service.dao;

import com.platform.soft.domain.IdBuilder;
import com.platform.soft.domain.Links;

public interface LinksDAO {

    int addLinks(Links links);

    int updateId(IdBuilder builder);

    Links findByKey(String key);
}
