package org.util.AoC2020;

import org.javatuples.Pair;

import java.util.List;
import java.util.Objects;

public class Sumtiply {
    public static boolean IsSum2020(int a, int b) {
        return (a + b == 2020);
    }

    public static List<Integer> GetSumOf2020(int[] numberList) {
        return GetNSumEqualsTo2020(numberList, 2);
    }

    private static List<Integer> GetNSumEqualsTo2020(int[] numberList, int n) {
        for (int i : numberList) {
            for (int j : numberList) {
                if (IsSum2020(i, j)) return List.of(i, j);
            }
        }
        return null;
    }

    public static int SumtiplicationOf(int[] baseList, int n) {
        return Objects.requireNonNull(GetNSumEqualsTo2020(baseList, n)).stream().reduce(1, (acc, i) -> acc * i);
    }

    public static int Sumtiplication(int[] baseList) {
        return SumtiplicationOf(baseList, 2);
    }

}
