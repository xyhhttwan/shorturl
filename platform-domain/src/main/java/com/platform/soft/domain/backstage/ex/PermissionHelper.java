package com.platform.soft.domain.backstage.ex;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 权限辅助类
 */
public class PermissionHelper  implements Serializable {

    private int id;

    private String text;

    private int parentId;



    private PermissionHelper permissionHelper;

    private List<PermissionHelper> children = new ArrayList<PermissionHelper>();

    public List<PermissionHelper> getChildren() {
        return children;
    }

    public void setChildren(List<PermissionHelper> children) {
        this.children = children;
    }

    public PermissionHelper getPermissionHelper() {
        return permissionHelper;
    }

    public void setPermissionHelper(PermissionHelper permissionHelper) {
        this.permissionHelper = permissionHelper;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
}
