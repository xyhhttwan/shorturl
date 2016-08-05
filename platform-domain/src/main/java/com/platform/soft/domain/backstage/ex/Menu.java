package com.platform.soft.domain.backstage.ex;

import com.platform.soft.common.utils.Status;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单实体类
 *
 * @author baixb
 * @version [v1.0，2015/6/17]
 */
public class Menu implements Serializable{

    /**
     * 主键
     */
    private Integer id;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 连接地址
     */
    private String url;

    /**
     * 菜单父id
     */
    private Integer parentId;


    /**

     * 菜单样式
     */
    private String icon;

    /**
     * 菜单排序
     */
    private int menuSort;


    /**
     * 菜单状态(0正常1不启用)
     */
    private int status;

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    private List<Menu> children = new ArrayList<Menu>();

    /**
     * 辅助作用不在数据库对应字段
     */
    private String text;

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getMenuSort() {
        return menuSort;
    }

    public void setMenuSort(int menuSort) {
        this.menuSort = menuSort;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "status=" + status +
                ", menuSort=" + menuSort +
                ", icon='" + icon + '\'' +
                ", parentId=" + parentId +
                ", url='" + url + '\'' +
                ", menuName='" + menuName + '\'' +
                ", id=" + id +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.text = menuName;
        this.menuName = menuName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.statusStr = Status.descriptionOf(status);
        this.status = status;
    }


}
