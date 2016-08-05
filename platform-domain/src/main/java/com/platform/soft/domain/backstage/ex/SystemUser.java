package com.platform.soft.domain.backstage.ex;



import com.platform.soft.common.utils.SexEnum;
import com.platform.soft.common.utils.Status;

import java.io.Serializable;
import java.util.Date;

/**
 * 后台用户实体类
 *
 * @author baixb
 * @version [v1.0，2015-06-16]
 */

public class SystemUser implements Serializable {

    /**
     * 主键 自动增长
     */
    private int id;

    /**
     * 用户名称
     */
    private String systemName;

    /**
     * 密码
     */

    private String password;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 身份证
     */
    private String IDCard;

    /**
     * 性别(0代表男1代表女)
     */
    private int sex;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 入职时间
     */
    private Date entryDate;

    /**
     * 用户状态(0正常-1删除)
     */
    private int status;

    /**
     * 状态的值 枚举中取
     */
    private String statusStr;

    /**
     * 性别枚举 不对应数据库的字段
     */
    private String sexStr;

    public String getSexStr() {
        return sexStr;
    }

    public void setSexStr(String sexStr) {
        this.sexStr = sexStr;
    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIDCard() {
        return IDCard;
    }

    public void setIDCard(String IDCard) {
        this.IDCard = IDCard;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sexStr = SexEnum.descriptionOf(sex);
        this.sex = sex;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "SystemUser{" +
                "id=" + id +
                ", systemName='" + systemName + '\'' +
                ", realName='" + realName + '\'' +
                ", IDCard='" + IDCard + '\'' +
                ", sex=" + sex +
                ", phone='" + phone + '\'' +
                ", entryDate=" + entryDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SystemUser that = (SystemUser) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
