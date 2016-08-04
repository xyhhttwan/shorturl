package com.platform.soft.service;

import com.platform.soft.dao.IBaseDAO;
import com.platform.soft.domain.BaseDomain;
import com.platform.soft.utils.SqlOrderEnum;

import java.util.List;
import java.util.Map;

/**
 * Created by baixiaobin on 16/8/4.
 */
public interface IBaseService<D extends IBaseDAO, T extends BaseDomain> {

    D getDao();

    int add(T entity);

    int edit(T entity);

    int delete(Object object);

    T view(Object object);

    List<T> listByPage(Map<String, Object> map, int offset, int rows);

    List<T> listByPage(Map condition, int offset, int rows, String orderBy, SqlOrderEnum sqlOrderEnum);

    int insert(T entity);

    int update(T entity);

    int insertMap(Map<String, Object> map);

    int updateMap(Map<String, Object> map);

    int updateNull(T entity);

    int deleteById(Object object);

    int deleteByIds(List list);

    int deleteByCondition(Map<String, Object> var1);

    int deleteByProperty(String var1, Object var2);

    T fetch(Object var1);

    T findOne(String property, Object value);

    List<T> findList(String property, Object value);

    List<T> findList(String property, Object value, String orderBy, SqlOrderEnum sqlOrderEnum);

    List<T> findAll();

    List<T> findAll(String var1, SqlOrderEnum sqlOrderEnum);

    List<T> queryPage(Map<String, Object> condition, int offset, int rows);

    List<T> queryPage(Map<String, Object> condition, int offset, int rows, String orderBy, SqlOrderEnum sqlOrderEnum);

    List<T> queryPage(Map<String, Object> condition, int offset, int rows, String orderBy, SqlOrderEnum sqlOrderEnum, Map<String, Object> selectorpage);

    List<T> like(Map<String, Object> condition);

    List<T> like(Map<String, Object> condition, String orderBy, SqlOrderEnum sqlOrderEnum);

    List<T> like(Map<String, Object> condition, String orderBy, SqlOrderEnum sqlOrderEnum, Map<String, Object> selector);

    List<T> queryList(Map condition, String orderBy, String sortBy);

    List<T> queryList(Map<String, Object> condition, String orderBy, String sortBy, Map<String, Object> selector);

    T queryOne(Map condition);

    T queryOne(Map condition, String orderBy, SqlOrderEnum sqlOrderEnum);

    T queryOne(Map<String, Object> condition, String orderBy, SqlOrderEnum sqlOrderEnum, Map<String, Object> selector);

    int count(Map condition);

    Object selectMaxId();

    void updateOrSave(T entity, Object id);

    T selectOne(String mapperId, Object obj);

    List<T> selectList(String mapperId, Object obj);

    int updateByCondition(Map<String, Object> updateMap, Map<String, Object> conditionMap);
}