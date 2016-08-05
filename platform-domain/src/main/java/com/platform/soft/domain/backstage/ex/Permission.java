package com.platform.soft.domain.backstage.ex;



import com.platform.soft.common.utils.Status;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 权限实体类
 *
 * @author baixb
 * @version [v1.0，2015/6/18]
 */
public class Permission implements Serializable {

    /**
     * 主键
     */
    private int id;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 权限编码
     */
    private String permissionCode;

    /**
     * 父id
     */
    private int parentId;

    /**
     * 状态0正常1不启用
     */
    private int status;

    /**
     * 状态的值 枚举中取
     */
    private String statusStr;

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    /**
     * 存放子类
     */
    private List<Permission> children= new ArrayList<Permission>();

    public List<Permission> getChildren() {
        return children;
    }

    public void setChildren(List<Permission> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", permissionName='" + permissionName + '\'' +
                ", permissionCode='" + permissionCode + '\'' +
                ", parentId=" + parentId +
                ", status=" + status +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.statusStr = Status.descriptionOf(status);
        this.status = status;
    }
}
