package com.platform.soft.dao;

import com.platform.soft.domain.BaseDomain;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by baixiaobin on 16/8/4.
 */
public interface IBaseDAO <T extends BaseDomain> {

    int insert(T entity);

    int update(T entity);

    int updateMap(@Param("map") Map<String, Object> var1);

    int updateByCondition(@Param("update") Map<String, Object> updateMap, @Param("condition") Map<String, Object> conditionMap);

    int insertMap(@Param("map") Map<String, Object> map);

    int updateNull(T entity);

    int deleteById(Object obj);

    int deleteByIds(List list);

    int deleteByCondition(Map<String, Object> map);

    int deleteByProperty(@Param("property") String property, @Param("value") Object value);

    T fetch(Object object);

    T findOne(@Param("property") String property, @Param("value") Object value, @Param("orderBy") String orderBy, @Param("sortBy") String sortBy);

    List<T> findList(@Param("property") String property, @Param("value") Object value, @Param("orderBy") String orderBy, @Param("sortBy") String sortBy);

    List<T> findAll(@Param("orderBy") String orderBy, @Param("sortBy") String sortBy);

    List<T> queryPage(@Param("condition") Map<String, Object> condition, @Param("offset") int offset, @Param("rows") int rows, @Param("orderBy") String orderBy, @Param("sortBy") String sortBy);

    List<T> queryPage(@Param("condition") Map<String, Object> condition, @Param("offset") int offset, @Param("rows") int rows, @Param("orderBy") String orderBy, @Param("sortBy") String sortBy, @Param("selector") Map<String, Object> selector);

    List<T> like(@Param("condition") Map<String, Object> condition, @Param("orderBy") String orderBy, @Param("sortBy") String sortBy);

    List<T> like(@Param("condition") Map<String, Object> condition, @Param("orderBy") String orderBy, @Param("sortBy") String sortBy, @Param("selector") Map<String, Object> selector);

    List<T> queryList(@Param("condition") Map<String, Object> condition, @Param("orderBy") String orderBy, @Param("sortBy") String sortBy);

    List<T> queryList(@Param("condition") Map<String, Object> condition, @Param("orderBy") String orderBy, @Param("sortBy") String sortBy, @Param("selector") Map<String, Object> selector);

    T queryOne(@Param("condition") Map<String, Object> condition, @Param("orderBy") String orderBy, @Param("sortBy") String sortBy);

    T queryOne(@Param("condition") Map<String, Object> condition, @Param("orderBy") String orderBy, @Param("sortBy") String sortBy, @Param("selector") Map<String, Object> selector);

    int count(Map<String, Object> condition);

    Object selectMaxId();

    T updateOrSave(@Param("condition") T condition, @Param("condition") Long var2);

    T selectOne(@Param("condition") String var1, @Param("condition") Object var2);

    //没实现
    List<T> selectList(@Param("condition") String var1, @Param("condition") Object condition);

    List<Map> queryBySql(@Param("executeSql") String executeSql);

    Long queryBySqlCount(@Param("executeSqlCount") String executeSqlCount);

    Class<T> getEntityClass();
}