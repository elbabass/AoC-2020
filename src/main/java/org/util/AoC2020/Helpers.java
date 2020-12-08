package org.util.AoC2020;

import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

public class Helpers {
    public static int[] listOfIntFromText(@NotNull String str) {
        String[] strNums = str.split("\n");
        return Stream.of(strNums).mapToInt(Integer::parseInt).toArray();
    }
}
