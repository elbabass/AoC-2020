package org.util.AoC2020.D05;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IdProcessor {
    public static OptionalInt min(int[] ints) {
        return Arrays.stream(ints).min();
    }

    public static OptionalInt max(int[] ints) {
        return Arrays.stream(ints).max();
    }

    public static OptionalInt hole(int[] ints) {
        final OptionalInt min = min(ints), max = max(ints);
        if (max.isEmpty() || min.isEmpty()) {
            return OptionalInt.empty();
        }
        final List<Integer> integerList = Arrays.stream(ints).boxed().collect(Collectors.toList());
        return IntStream.rangeClosed(min.getAsInt(), max.getAsInt())
                .boxed()
                .collect(Collectors.toList())
                .stream()
                .filter(i -> !integerList.contains(i))
                .findFirst().map(OptionalInt::of)
                .orElse(OptionalInt.empty());
    }
}
