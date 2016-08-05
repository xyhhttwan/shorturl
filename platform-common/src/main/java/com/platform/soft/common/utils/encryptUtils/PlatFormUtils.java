package com.platform.soft.common.utils.encryptUtils;


import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * 本系统自定义加密规则
 */
public class PlatFormUtils {
    public static char[] zm = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 'u', 'v', 'w', 'x', 'y', 'z', 't'};//不能改;

    /**
     * 帐户加密规则，
     */
    public static String AccountIdEncrypt(String accountId) {
        if (StringUtils.isNotEmpty(accountId) && NumberUtils.isNumber(accountId)) {
            int rule = 0;
            char cr = accountId.charAt(6);
            rule = NumberUtils.toInt(cr + "");
            if (rule == 0) {
                rule = 6;
            }
            String result = String.valueOf(zm[rule]);
            for (int i = 0; i < accountId.length(); i++) {
                int value = NumberUtils.toInt(accountId.charAt(i) + "") * rule;
                if (value > 24) {
                    int s = value / 10;
                    int g = value % 10;
                    result = result + zm[25] + zm[s] + zm[g];
                } else {
                    result = result + zm[value];
                }
            }
            return result;
        }
        return accountId;
    }

    /**
     * 帐户加密规则，
     */
    public static String AccountIdDecrypt(String enAccountId) {

        if (StringUtils.isNotEmpty(enAccountId)) {
            String result = "";
            char cr = enAccountId.charAt(0);
            int rule = ArrayUtils.indexOf(zm, cr);
            if (rule == 0) {
                rule = 6;
            }
            for (int i = 1; i < enAccountId.length(); i++) {
                char c = enAccountId.charAt(i);
                if (c == zm[25]) {
                    int sInt = ArrayUtils.indexOf(zm, enAccountId.charAt(i + 1)) * 10;
                    int gInt = ArrayUtils.indexOf(zm, enAccountId.charAt(i + 2));
                    result = result + (sInt + gInt) / rule;
                    i = i + 2;
                } else {
                    result = result + ArrayUtils.indexOf(zm, c) / rule;
                }
            }
            return result;
        }

        return enAccountId;
    }


    public static void main(String args[]) {
        System.out.println(AccountIdEncrypt("15828908561"));
        System.out.println(AccountIdDecrypt("ggtdateimteitfeateitdatdgg"));
    }
}
