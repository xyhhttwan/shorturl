package com.platform.soft.domain.backstage.ex;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单辅助类
 */
public class MenuHelper implements Serializable {

    private int id;

    private String text;

    private Integer parentId;



    private MenuHelper menuHelper;

    public List<MenuHelper> getChildren() {
        return children;
    }

    public void setChildren(List<MenuHelper> children) {
        this.children = children;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public MenuHelper getMenuHelper() {
        return menuHelper;
    }

    public void setMenuHelper(MenuHelper menuHelper) {
        this.menuHelper = menuHelper;
    }

    private List<MenuHelper> children = new ArrayList<MenuHelper>();
}
