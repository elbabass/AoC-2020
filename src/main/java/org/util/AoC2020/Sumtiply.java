package org.util.AoC2020;

import org.javatuples.Pair;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

public class Sumtiply {
    public static boolean IsSum2020(int a, int b) {
        return (a + b == 2020);
    }

    public static int[] listOfIntFromText(@NotNull String str) {
        String[] strNums = str.split("\n");
        return Stream.of(strNums).mapToInt(Integer::parseInt).toArray();
    }

    public static Pair<Integer, Integer> GetSumOf2020(int[] numberList) {
        for (int n : numberList) {
            for (int m : numberList) {
                if (IsSum2020(n, m)) return Pair.with(n, m);
            }
        }
        return Pair.with(0, 0);
    }

    public static int Sumtiplication(int[] baseList) {
        Pair<Integer, Integer> pair = GetSumOf2020(baseList);
        return pair.getValue0()*pair.getValue1();
    }
}
