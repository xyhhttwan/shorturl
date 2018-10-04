package com.platform.soft.utils;

public class UrlShortener {

    private static final String ALPHABET = "HDabcdeghjklmo10000089LpqrstuvwxyzABCfEFGIJnKLMOPQRSiTUVWXYZ0123456789";
    private static final int    BASE     = ALPHABET.length();

    public static String encode(int num) {
        StringBuilder sb = new StringBuilder();
        while ( num > 0 ) {
            sb.append( ALPHABET.charAt( num % BASE ) );
            num /= BASE;
        }
        return sb.reverse().toString();
    }

    public static int decode(String str) {
        int num = 0;
        for ( int i = 0; i < str.length(); i++ )
            num = num * BASE + ALPHABET.indexOf(str.charAt(i));
        return num;
    }

    public static void main(String[] args) {
        System.out.println(encode(10000089));
    }
}
