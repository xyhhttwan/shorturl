package com.platform.soft.base.springmvc;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;

public class BackStageController extends BaseController {
    /**
     * 当前页默认为0
     */
    protected int page = 0;

    /**
     * 每页显示15条记录
     */
    protected int rows = 15;


    /**
     * 默认获取 页数和 总的记录数
     */
    @ModelAttribute
    protected void initPageRows() {

        String pages = request.getParameter("page");
        String rows_ = request.getParameter("rows");

        if (StringUtils.isNotEmpty(pages)) {
            page = Integer.parseInt(pages);
        }

        if (StringUtils.isNotEmpty(rows_)) {
            rows = Integer.parseInt(rows_);
        }
    }
}
