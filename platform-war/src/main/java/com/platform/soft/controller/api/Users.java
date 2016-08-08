package com.platform.soft.controller.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by baixiaobin on 16/8/6.
 */
@ApiModel
public class Users {

    @ApiModelProperty(name="主键id",required = true)
    private String id;
    @ApiModelProperty(name="用户名",required = true)
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
