/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: platform
 * $Id:  DynamicOrderServiceImpl.java 2016-08-10 21:01:21 $
 */
package com.platform.soft.service.service.backstage;


import com.platform.soft.api.backstage.IDynamicOrderService;
import com.platform.soft.common.dao.IBaseDAO;
import com.platform.soft.common.service.AbstractPageService;
import com.platform.soft.domain.backstage.DynamicOrder;
import com.platform.soft.service.dao.backstage.IDynamicOrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("DynamicOrderServiceImpl")
public class DynamicOrderServiceImpl extends AbstractPageService<IBaseDAO<DynamicOrder>, DynamicOrder> implements IDynamicOrderService<IBaseDAO<DynamicOrder>,DynamicOrder> {
    @Autowired
    private IDynamicOrderDAO dynamicOrderDAO;

    @Override
    public IBaseDAO<DynamicOrder> getDao() {
        return dynamicOrderDAO;
    }

//    @Override
//    public void insert(BaseDomain entity) {
//
//    }
//
//    @Override
//    public void update(BaseDomain entity) {
//
//    }
//
//    @Override
//    public void updateNull(BaseDomain entity) {
//
//    }
//
//    @Override
//    public void deleteById(Long id) {
//
//    }
//
//    @Override
//    public void deleteByProperty(String property, Object value) {
//
//    }
//
//    @Override
//    public BaseDomain fetch(Long id) {
//        return null;
//    }
//
//    @Override
//    public BaseDomain findOne(@Param("property") String property, @Param("value") Object value) {
//        return null;
//    }
//
//    @Override
//    public List findList(String property, Object value) {
//        return null;
//    }
//
//    @Override
//    public void deleteByCondition(Map condition) {
//
//    }
//
//    @Override
//    public void updateMap(@Param("map") Map entityMap) {
//
//    }
//
//    @Override
//    public List<DynamicOrder> findAll() {
//        return dynamicOrderDAO.findAll();
//    }
//
//    @Override
//    public List like(String property, Object value) {
//        return null;
//    }
//
//    @Override
//    public Long selectMaxId() {
//        return null;
//    }
//
//    @Override
//    public BaseDomain updateOrSave(BaseDomain baseDomain, Long id) {
//        return null;
//    }
//
//    @Override
//    public BaseDomain selectOne(String mapperId, Object obj) {
//        return null;
//    }
//
//    @Override
//    public List selectList(String mapperId, Object obj) {
//        return null;
//    }
//
//    @Override
//    public Class getEntityClass() {
//        return null;
//    }
//
//    @Override
//    public int count(Map condition) {
//        return 0;
//    }
//
//    @Override
//    public BaseDomain queryOne(Map condition) {
//        return null;
//    }
//
//    @Override
//    public List queryList(@Param("condition") Map condition, @Param("orderBy") String orderBy, @Param("sortBy") String sortBy) {
//        return null;
//    }
//
//    @Override
//    public List queryPage(@Param("condition") Map condition, @Param("offset") int offset, @Param("rows") int rows) {
//        return null;
//    }
//
//    @Override
//    protected DynamicOrderDAO getDao() {
//        return dynamicOrderDAO;
//    }
//
//    @Override
//    public BizData4Page queryPageByDataPerm(String resUri, Map conditions, int curPage, int offset, int rows) {
//        return super.doQueryPageByDataPerm(resUri, conditions, curPage, offset, rows);
//    }


}
