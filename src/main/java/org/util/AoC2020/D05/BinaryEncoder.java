package org.util.AoC2020.D05;

public class BinaryEncoder {
    public static int encode(String encoded, char oneChar) {
        final int length = encoded.length();
        int number = 0;
        for (int i = length - 1; i >= 0; i--) {
            if (encoded.charAt(i) == oneChar) {
                number += Math.pow(2,(length - i - 1));
            }
        }
        return number;
    }
}
