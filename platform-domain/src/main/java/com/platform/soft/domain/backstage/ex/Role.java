package com.platform.soft.domain.backstage.ex;


import com.platform.soft.common.utils.Status;

import java.io.Serializable;

/**
 * 系统角色实体类
 *
 * @author baixb
 * @version [v1.0，2015/6/17]
 */
public class Role implements Serializable {

    /**
     * 主键id
     */
    private int id;

    /**
     * 角色名称
     */
    private String roleName;


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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.statusStr = Status.descriptionOf(status);
        this.status = status;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
