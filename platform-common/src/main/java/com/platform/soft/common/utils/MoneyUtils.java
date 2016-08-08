package com.platform.soft.common.utils;

import java.math.BigDecimal;

/**
 * Created by baixiaobin on 16/8/8.
 */
public class MoneyUtils {

    /**
     * 默认的精确度
     */
    private static final int DEF_DIV_SCALE = 2;

    /**
     * 该类不能被实例化
     */
    private MoneyUtils() {
    }

    /**
     * 钱的加法
     *
     * @author zz
     */
    public static final float add(float v1, float v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.add(b2).floatValue();
    }

    /**
     * 钱的减法
     *
     * @param v1
     *            被减数
     * @param v2
     *            减数
     * @author zz
     */
    public static final float sub(float v1, float v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.subtract(b2).floatValue();
    }

    /**
     * 钱的乘法
     * @param scala 保留几位小数
     * @author zz
     */
    public static final float mul(float v1, float v2,int scala) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return round(b1.multiply(b2).floatValue(),scala);
    }
    /**
     * 钱的乘法 保留两位小数
     * @author zz
     */
    public static final float mul(float v1, float v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return round(b1.multiply(b2).floatValue(),DEF_DIV_SCALE);
    }

    /**
     * 钱的除法(四舍五入)
     *
     * @param v1
     *            被除数
     * @parama v2 除数
     * @param scala
     *            表示表示需要精确到小数点以后几位。
     * @author zz
     */
    public static final float div(float v1, float v2, int scala) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.divide(b2,scala,BigDecimal.ROUND_HALF_UP).floatValue();
    }
    /**
     * 钱的除法(四舍五入) 默认保留两位小数
     * @author zz
     */
    public static final float div(float v1, float v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.divide(b2,DEF_DIV_SCALE,BigDecimal.ROUND_HALF_UP).floatValue();
    }

    /**
     * 提供精确的小数位四舍五入处理。
     *
     * @param v
     *            需要四舍五入的数字
     * @param scale
     *            小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static float round(float v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).floatValue();
    }
}
