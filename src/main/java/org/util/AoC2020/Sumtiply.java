package org.util.AoC2020;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Sumtiply {
    public static List<Integer> GetSumOf2020(int[] numberList) {
        return GetNSumEqualsTo2020(numberList, 2);
    }

    private static List<Integer> GetNSumEqualsTo2020(int[] numberList, int n) {
        for (int i : numberList) {
            for (int j : numberList) {
                if (i + j == 2020) return List.of(i, j);
            }
        }
        return List.of();
    }

    public static Optional<Integer> SumtiplicationOf(int[] numberList, int n) {
        var sumIs2020List = GetNSumEqualsTo2020(numberList, n);
        if (sumIs2020List.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(sumIs2020List.stream().reduce(1, (acc, i) -> acc * i));
    }

    public static Optional<Integer> Sumtiplication(int[] numberList) {
        return SumtiplicationOf(numberList, 2);
    }
}
