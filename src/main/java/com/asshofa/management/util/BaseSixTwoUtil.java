package com.asshofa.management.util;

import java.math.BigInteger;

public class BaseSixTwoUtil {

    private BaseSixTwoUtil() {}

    private static final String BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static String encode(byte[] input) {
        BigInteger value = new BigInteger(1, input);
        StringBuilder result = new StringBuilder();
        while (value.compareTo(BigInteger.ZERO) > 0) {
            BigInteger[] divmod = value.divideAndRemainder(BigInteger.valueOf(62));
            result.insert(0, BASE62.charAt(divmod[1].intValue()));
            value = divmod[0];
        }
        return result.toString();
    }

    public static byte[] decode(String input) {
        BigInteger value = BigInteger.ZERO;
        for (char c : input.toCharArray()) {
            value = value.multiply(BigInteger.valueOf(62)).add(BigInteger.valueOf(BASE62.indexOf(c)));
        }
        return value.toByteArray();
    }
}

