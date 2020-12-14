package org.util.AoC2020.D01;

import org.apache.commons.collections4.ListUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Sumtiply {
    public static List<Integer> GetSumOf2020(int[] numberList) {
        return GetNSumEqualsTo2020(numberList, 2);
    }

    private static List<Integer> GetNSumEqualsTo2020(int[] numberList, int n) {
        List<Integer> integerSumming2020List = null;
        if (n == 2) {
            integerSumming2020List = get2SumEqualsTo2020(numberList);
        }
        else if (n == 3) {
            integerSumming2020List = get3SumEqualsTo2020(numberList);
        }
        if (integerSumming2020List != null) return integerSumming2020List;
        return List.of();
    }

    private static List<Integer> get3SumEqualsTo2020(int[] numberList) {
        for (int i : numberList) {
            for (int j : numberList) {
                List<Integer> listNum = getComplementTo2020(getRest(numberList), List.of(i,j));
                if (listNum != null) return listNum;
            }
        }
        return null;
    }

    @Nullable
    private static List<Integer> get2SumEqualsTo2020(int[] numberList) {
        for (int j : numberList) {
            List<Integer> listNum = getComplementTo2020(getRest(numberList), List.of(j));
            if (listNum != null) return listNum;
        }
        return null;
    }

    @NotNull
    private static int[] getRest(int[] numberList) {
        return Arrays.copyOfRange(numberList, 1, numberList.length);
    }

    @Nullable
    private static List<Integer> getComplementTo2020(int[] numberList, List<Integer> origList) {
        for (int number : numberList) {
            List<Integer> listNum = ListUtils.union(origList, List.of(number));
            if (listNum.stream().reduce(0, Integer::sum) == 2020) return listNum;
        }
        return null;
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
