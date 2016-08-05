package com.platform.soft.base.enums;

public enum MessageCode {


    DEFAULT_SUCCESS("00000", "操作成功"),
    DEFAULT_FAILED("10001", "操作失败"),
    LOGIN_FAILED("10002", "登录失败"),

    ADD_FAILED("10003", "添加失败"),
    DELETE_FAILED("10004", "删除失败"),
    UPDATE_FAILED("10005", "修改失败"),
    QUERY_FAILED("10006", "查询失败"),
    ROLE_NAME_IS_EXIST("10007", "该角色名已经存在"),
    MENU_SETTING_FAILED("10008", "菜单设置失败"),
    PERMISSION_SETTING_FAILED("10009", "权限设置失败"),
    MENU_HAS_CHILDREN("10010", "该条记录存在子类不能删除"),
    USER_NAME_IS_EXIST("10011", "用户名已经存在"),
    SETTING_FAILED("10012", "设置失败"),
    USER_PASSWORD_ERROR("10013", "密码错误"),
    PARAM_EMPTY("10014", "参数不能为空"),
    VERIFYCODE_EMPTY("10015", "验证码不正确"),
    USER_EMPTY("10016", "用户名或者密码错误"),
    LOGIN_ERROR_TOO_MUCH("10017", "账户错误次数过多,暂时禁止登录"),
    ACCOUNT_NAME_IS_EXIST("10018", "账户名已经存在"),
    SYSTEM_ERROR_EXCEPTION("10019", "系统异常"),
    UNKNOWN_ERROR("10020","位置异常" );

    private String code;
    private String message;

    MessageCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
