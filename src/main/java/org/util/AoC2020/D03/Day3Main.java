package org.util.AoC2020.D03;

import org.util.AoC2020.Helpers;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Day3Main {
    static final String basePath = "d03-inputs.txt";
    private static final List<char[]> mapPatterns =
            Objects.requireNonNull(Helpers.streamedStringsFromFile(basePath))
                    .map(String::toCharArray)
                    .collect(Collectors.toList());

    public static void main(String[] args) {
        TreePath treePath = new TreePath(mapPatterns);
        long[] treesOnSlope = new long[5];
        treesOnSlope[0] = treePath.treesOnSlope(1, 1);
        treesOnSlope[1] = treePath.treesOnSlope(1, 3);
        treesOnSlope[2] = treePath.treesOnSlope(1, 5);
        treesOnSlope[3] = treePath.treesOnSlope(1, 7);
        treesOnSlope[4] = treePath.treesOnSlope(2, 1);

        final long multiplication = LongStream.of(treesOnSlope).reduce(1, (a, b) -> a * b);

        System.out.println("Total number of trees : " + treesOnSlope[1]);

        System.out.println("Multiplication of all trees is : " + multiplication);
    }
}
