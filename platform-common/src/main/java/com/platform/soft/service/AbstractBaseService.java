package com.platform.soft.service;

/**
 * Created by baixiaobin on 16/8/4.
 */

import com.platform.soft.dao.IBaseDAO;
import com.platform.soft.dao.IDaoAware;
import com.platform.soft.domain.BaseDomain;
import com.platform.soft.domain.CreateBaseDomain;
import com.platform.soft.utils.SqlOrderEnum;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@CacheConfig(
        cacheNames = {"allunused"}
)
public abstract class AbstractBaseService<D extends IBaseDAO, T extends BaseDomain> implements IBaseService<D, T>, IDaoAware<D, T> {

    public AbstractBaseService() {
    }

    public final int add(T entity) {
        this.enhanceNewCreateBaseDomain(entity);
        return this.getDao().insert(entity);
    }

    @CacheEvict(
            key = "#entity.id"
    )
    public final int edit(T entity) {
        this.enhanceCreateBaseDomain(entity);
        return this.getDao().update(entity);
    }

    @CacheEvict(
            key = "#id"
    )
    public final int delete(Object id) {
        return this.getDao().deleteById(id);
    }

    public final int deleteByIds(List list) {
        return this.getDao().deleteByIds(list);
    }

    @Cacheable(
            key = "#id"
    )
    public final T view(Object id) {
        return (T) this.getDao().fetch(id);
    }

    public final int insert(T entity) {
        this.enhanceNewCreateBaseDomain(entity);
        return this.getDao().insert(entity);
    }

    @CacheEvict(
            key = "#entity.id"
    )
    public final int update(T entity) {
        this.enhanceCreateBaseDomain(entity);
        return this.getDao().update(entity);
    }

    @CacheEvict(
            key = "#entity.id"
    )
    public final int updateNull(T entity) {
        this.enhanceCreateBaseDomain(entity);
        return this.getDao().updateNull(entity);
    }

    @CacheEvict(
            key = "#entity.id"
    )
    public final int deleteById(Object id) {
        return this.getDao().deleteById(id);
    }

    @CacheEvict(
            allEntries = true
    )
    public final int deleteByProperty(String property, Object value) {
        return this.getDao().deleteByProperty(property, value);
    }

    @Cacheable(
            key = "#id"
    )
    public final T fetch(Object id) {
        return (T) this.getDao().fetch(id);
    }

    @Cacheable
    public final T findOne(String property, Object value) {
        return (T) this.getDao().findOne(property, value, (String) null, (String) null);
    }

    @Cacheable
    public final List findList(String property, Object value) {
        return this.getDao().findList(property, value, (String) null, (String) null);
    }

    @Cacheable
    public final List findList(String property, Object value, String orderBy, SqlOrderEnum sqlOrderEnum) {
        return this.getDao().findList(property, value, orderBy, sqlOrderEnum.getAction());
    }

    public final List findAll() {
        return this.getDao().findAll((String) null, (String) null);
    }

    public final List findAll(String orderBy, SqlOrderEnum sqlOrderEnum) {
        return this.getDao().findAll(orderBy, sqlOrderEnum.getAction());
    }

    @Cacheable
    public final List like(Map<String, Object> condition) {
        return this.getDao().like(condition, (String) null, (String) null, (Map) null);
    }

    @Cacheable
    public final List like(Map<String, Object> condition, String orderBy, SqlOrderEnum sqlOrderEnum) {
        return this.getDao().like(condition, orderBy, sqlOrderEnum.getAction(), (Map) null);
    }

    public final Object selectMaxId() {
        return this.getDao().selectMaxId();
    }

    @CacheEvict(
            key = "#id"
    )
    public final void updateOrSave(T entity, Object id) {
        if (id != null && !StringUtils.isEmpty(id)) {
            this.enhanceCreateBaseDomain(entity);
            this.getDao().update(entity);
        } else {
            this.enhanceNewCreateBaseDomain(entity);
            this.getDao().insert(entity);
        }

    }

    public final T selectOne(String mapperId, Object obj) {
        return (T) this.getDao().selectOne(mapperId, obj);
    }

    public final List selectList(String mapperId, Object obj) {
        return this.getDao().selectList(mapperId, obj);
    }

    public final int count(Map condition) {
        return this.getDao().count(condition);
    }

    public final T queryOne(Map condition) {
        return (T) this.getDao().queryOne(condition, (String) null, (String) null, (Map) null);
    }

    public final T queryOne(Map condition, String orderBy, SqlOrderEnum sqlOrderEnum) {
        return (T) this.getDao().queryOne(condition, orderBy, sqlOrderEnum.getAction(), (Map) null);
    }

    public final List queryList(Map condition, String orderBy, String sortBy) {
        return this.getDao().queryList(condition, orderBy, sortBy, (Map) null);
    }

    public final List queryPage(Map condition, int offset, int rows) {
        return this.getDao().queryPage(condition, offset, rows, (String) null, (String) null, (Map) null);
    }

    public List<T> queryPage(Map<String, Object> condition, int offset, int rows, String orderBy, SqlOrderEnum sqlOrderEnum) {
        return this.getDao().queryPage(condition, offset, rows, orderBy, sqlOrderEnum.getAction(), (Map) null);
    }

    @CacheEvict(
            allEntries = true
    )
    public final int deleteByCondition(Map condition) {
        return this.getDao().deleteByCondition(condition);
    }

    @CacheEvict(
            allEntries = true
    )
    public final int updateMap(Map entityMap) {
        this.enhanceCreateBaseDomain(entityMap);
        return this.getDao().updateMap(entityMap);
    }

    public final int insertMap(Map entityMap) {
        this.enhanceNewCreateBaseDomain(entityMap);
        return this.getDao().insertMap(entityMap);
    }

    public final List listByPage(Map condition, int offset, int rows) {
        return this.getDao().queryPage(condition, offset, rows, (String) null, (String) null, (Map) null);
    }

    public final List listByPage(Map condition, int offset, int rows, String orderBy, SqlOrderEnum sqlOrderEnum) {
        return this.getDao().queryPage(condition, offset, rows, orderBy, sqlOrderEnum.getAction(), (Map) null);
    }

    private final T enhanceCreateBaseDomain(T entity) {
        if (entity instanceof CreateBaseDomain) {
            ((CreateBaseDomain) entity).setLastModDate(Long.valueOf(System.currentTimeMillis()));
        }

        return entity;
    }

    private final T enhanceNewCreateBaseDomain(T entity) {
        if (entity instanceof CreateBaseDomain) {
            if (((CreateBaseDomain) entity).getCreateDate() == null) {
                ((CreateBaseDomain) entity).setCreateDate(Long.valueOf(System.currentTimeMillis()));
            }

            if (((CreateBaseDomain) entity).getStatus() == null) {
                ((CreateBaseDomain) entity).setStatus(Integer.valueOf(0));
            }

            if (((CreateBaseDomain) entity).getLastModDate() == null) {
                ((CreateBaseDomain) entity).setLastModDate(Long.valueOf(0L));
            }

            if (((CreateBaseDomain) entity).getCreator() == null) {
                ((CreateBaseDomain) entity).setCreator(Long.valueOf(0L));
            }

            if (((CreateBaseDomain) entity).getLastModifier() == null) {
                ((CreateBaseDomain) entity).setLastModifier(Long.valueOf(0L));
            }
        }

        return entity;
    }

    private final Map enhanceCreateBaseDomain(Map entityMap) {
        entityMap.put("lastModDate", Long.valueOf(System.currentTimeMillis()));
        return entityMap;
    }

    private final Map enhanceNewCreateBaseDomain(Map entityMap) {
        entityMap.put("createDate", Long.valueOf(System.currentTimeMillis()));
        return entityMap;
    }

    public List<T> queryPage(Map<String, Object> condition, int offset, int rows, String orderBy, SqlOrderEnum sqlOrderEnum, Map<String, Object> selectorpage) {
        return this.getDao().queryPage(condition, offset, rows, orderBy, sqlOrderEnum.getAction(), selectorpage);
    }

    public List<T> like(Map<String, Object> condition, String orderBy, SqlOrderEnum sqlOrderEnum, Map<String, Object> selector) {
        return this.getDao().like(condition, orderBy, sqlOrderEnum.getAction(), selector);
    }

    public List<T> queryList(Map<String, Object> condition, String orderBy, String sortBy, Map<String, Object> selector) {
        return this.getDao().queryList(condition, orderBy, sortBy, selector);
    }

    public T queryOne(Map<String, Object> condition, String orderBy, SqlOrderEnum sqlOrderEnum, Map<String, Object> selector) {
        return (T) this.getDao().queryOne(condition, orderBy, sqlOrderEnum.getAction(), selector);
    }

    public int updateByCondition(Map<String, Object> updateMap, Map<String, Object> conditionMap) {
        return this.getDao().updateByCondition(updateMap, conditionMap);
    }
}