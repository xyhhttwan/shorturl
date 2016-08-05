package com.platform.soft.common.utils;

/**
 * 性别枚举
 *
 * @author baixb
 * @version [v1.0，2015/7/2]
 */
public enum SexEnum {

    MAN(0, "未知"),
    WOMAN(1, "女"),
    UNKOWN(2, "男");
    private int index;
    private String description;

    SexEnum(int index, String description) {
        this.index = index;
        this.description = description;
    }

    private int getIndex() {
        return index;
    }

    private String getDescription() {
        return description;
    }

    public static String descriptionOf(int index) {
        SexEnum sexEnum = typeOf(index);
        if (null != sexEnum) {
            return sexEnum.getDescription();
        } else {
            return "未知";
        }
    }

    private static SexEnum typeOf(int index) {

        for (SexEnum type : values()) {
            if (index == type.index) {
                return type;
            }
        }
        return null;
    }


    public static void main(String args[]) {

        System.out.println(SexEnum.MAN.getDescription());
    }
}
