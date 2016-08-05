package com.platform.soft.common.service;

import com.platform.soft.common.dao.IBaseDAO;
import com.platform.soft.common.domain.BaseDomain;
import com.platform.soft.common.domain.BizData4Page;
import com.platform.soft.common.utils.SqlOrderEnum;

import java.util.Map;

/**
 * Created by baixiaobin on 16/8/4.
 */
public interface IPageService<D extends IBaseDAO, T extends BaseDomain> {

    BizData4Page queryPageByDataPerm(String var1, Map<String, Object> var2, int var3, int var4, int var5);

    void queryPageByDataPerm(BizData4Page var1);

    void queryPageByDataPerm(IBaseDAO var1, BizData4Page var2);

    void queryPageByDataPerm(BizData4Page var1, String var2, SqlOrderEnum var3);

    BizData4Page queryPageByDataPerm(String var1, Map<String, Object> var2, int var3, int var4, int var5, String var6, SqlOrderEnum var7);

    void queryPageByDataPerm(BizData4Page var1, String var2, SqlOrderEnum var3, Map<String, Object> var4);

    void queryPageByDataPerm(IBaseDAO var1, BizData4Page var2, String var3, SqlOrderEnum var4, Map<String, Object> var5);

    BizData4Page queryPageByDataPerm(IBaseDAO var1, Map<String, Object> var2, int var3, int var4, int var5, String var6, SqlOrderEnum var7, Map<String, Object> var8);
}
