package org.util.AoC2020.D05;

import org.util.AoC2020.Helpers;

import java.util.Objects;
import java.util.OptionalInt;

public class Day5Main {
    static final String basePath = "d05-inputs.txt";

    public static void main(String[] args) {
        final int[] ids = Objects.requireNonNull(Helpers.streamedStringsFromFile(basePath))
                .map(PlaneSeat::new)
                .mapToInt(PlaneSeat::getId)
                .toArray();
        final OptionalInt maxId = IdProcessor.max(ids);
        final OptionalInt holeId = IdProcessor.hole(ids);

        System.out.println("The highest seat Id from the list is : " + maxId);
        System.out.println("The empty place is : " + holeId);
    }
}
