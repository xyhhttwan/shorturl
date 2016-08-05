package com.platform.soft.common.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by baixiaobin on 16/8/4.
 */
public class BizData4Page<T> implements Serializable {
        private int page = 1;
        private int total = 0;
        private int pagesize = 10;
        private int records = 0;
        private List<T> rows;
        private Map<String, Object> conditions = new HashMap();
        private Map<List, Object> userdata;

        public BizData4Page() {
        }

        public Map<String, Object> getConditions() {
            return this.conditions;
        }

        public void setConditions(Map<String, Object> conditions) {
            this.conditions = conditions;
        }

        public int getPage() {
            return this.page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getTotal() {
            return this.total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getRecords() {
            return this.records;
        }

        public void setRecords(int records) {
            this.records = records;
        }

        public List<T> getRows() {
            return this.rows;
        }

        public void setRows(List<T> rows) {
            this.rows = rows;
        }

        public Map<List, Object> getUserdata() {
            return this.userdata;
        }

        public void setUserdata(Map<List, Object> userdata) {
            this.userdata = userdata;
        }

        public int getPagesize() {
            return this.pagesize;
        }

        public void setPagesize(int pagesize) {
            this.pagesize = pagesize;
        }
}
