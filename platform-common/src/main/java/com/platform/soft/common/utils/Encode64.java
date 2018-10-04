package com.platform.soft.common.utils;

public class Encode64 {

    final static char[] digits = { 'Z', 'u', '2', 'r', '4', 'j', '6', 'o', '8',
            'z', 'S', 'B', 'C', 'Q', 'E', '4', 'G', 'H', 'I', 'J', 'K', 'U',
            '9', 'a', 'b', 'c', 'd', 'P', 'f', 'g', 'h', 'i', '5', 'k', 'l',
            'm', 'n', '7', 'p', 'q', '3', 's', 't', '1', 'W', 'w', 'x', 'y',
            'X', 'N', 'O', 'e', 'D', 'R', 'A', 'T', 'L', 'V', 'v', 'M', 'Y',
            '0', '+', '/', };

    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        long timeMillins = 10000089L;
        System.out.println("初始值:" + timeMillins);
        String tmp = CompressNumber(timeMillins);
        System.out.println("加密后:" + tmp);
        System.out.println("解密后:" + UnCompressNumber(tmp));
    }

    /**
     * 把10进制的数字转换成64进制
     *
     * @param number
     * @param shift
     * @return
     */
    public static String CompressNumber(long number) {
        char[] buf = new char[64];
        int charPos = 64;
        int radix = 1 << 6;
        long mask = radix - 1;
        do {
            buf[--charPos] = digits[(int) (number & mask)];
            number >>>= 6;
        } while (number != 0);
        return new String(buf, charPos, (64 - charPos));
    }

    /**
     * 把64进制的字符串转换成10进制
     *
     * @param decompStr
     * @return
     */
    private static long UnCompressNumber(String decompStr) {
        long result = 0;
        for (int i = decompStr.length() - 1; i >= 0; i--) {
            for (int j = 0; j < digits.length; j++) {
                if (decompStr.charAt(i) == digits[j]) {
                    result += ((long) j) << 6 * (decompStr.length() - 1 - i);
                }
            }
        }
        return result;
    }



}
