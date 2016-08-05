package com.platform.soft.common.utils;

/**
 * Created by baixiaobin on 16/8/4.
 */
public enum  Status{

        NORMAL(0, "正常"),

        FREEZE(-1, "删除");

        private int index;
        private String description;

                Status(int index, String description) {
                this.index = index;
                this.description = description;
                }

        public int getIndex() {
                return index;
                }

        private String getDescription() {
                return description;
                }

        public static String descriptionOf(int index) {
                Status statusEnum = typeOf(index);
                if (null != statusEnum) {
                return statusEnum.getDescription();
                } else {
                return "";
                }
                }

        private static Status typeOf(int index) {

                for (Status type : values()) {
                if (index == type.index) {
                return type;
                }
                }
                return null;
                }

        }