package com.platform.soft.common.utils;

import com.platform.soft.common.domain.BizData4Page;
import com.platform.soft.common.dao.IBaseDAO;

import java.util.List;
import java.util.Map;

/**
 * Created by baixiaobin on 16/8/4.
 */
public class BizData4PageBuilder  {
        public BizData4PageBuilder() {

        }

        public static BizData4Page createBizData4Page(IBaseDAO dao, Map<String, Object> conditions, int curPage, int offset, int rows) {
            List mainData = dao.queryPage(conditions, offset, rows, (String)null, (String)null, (Map)null);
            int records = dao.count(conditions);
            BizData4Page bizData4Page = new BizData4Page();
            bizData4Page.setRows(mainData);
            bizData4Page.setPage(curPage);
            bizData4Page.setRecords(records);
            int total = records / rows;
            int mod = records % rows;
            if(mod > 0) {
                ++total;
            }

            bizData4Page.setTotal(total);
            return bizData4Page;
        }
}