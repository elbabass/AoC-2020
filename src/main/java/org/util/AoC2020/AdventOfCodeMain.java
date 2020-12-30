package org.util.AoC2020;

public class AdventOfCodeMain {
    public static String getDailyInputFile(int dayNumber) {
        return String.format("%s%02d%s", "d", dayNumber, "-inputs.txt");
    }
}
