package org.util.AoC2020.D05;

import org.util.AoC2020.AdventOfCodeMain;
import org.util.AoC2020.Helpers;

import java.util.Objects;
import java.util.OptionalInt;

public class Day05Main extends AdventOfCodeMain {

    public static void main(String[] args) {
        final int[] ids = Objects.requireNonNull(Helpers.streamedStringsFromFile(getDailyInputFile(5)))
                .map(PlaneSeat::new)
                .mapToInt(PlaneSeat::getId)
                .toArray();
        final OptionalInt maxId = IdProcessor.max(ids);
        final OptionalInt holeId = IdProcessor.hole(ids);

        System.out.println("The highest seat Id from the list is : " + maxId);
        System.out.println("The empty place is : " + holeId);
    }
}
