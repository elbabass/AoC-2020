package org.util.AoC2020.D01;

import org.util.AoC2020.AdventOfCodeMain;
import org.util.AoC2020.Helpers;

public class Day01Main extends AdventOfCodeMain {

    public static void main(String[] args) {
        int[] listOfInput1 = Helpers.getConvertedIntInputs(getDailyInputFile(1), Integer::parseInt);

        Sumtiply
                .SumtiplicationOf(listOfInput1, 2)
                .ifPresentOrElse(System.out::println, printIfNotPresent(2));
        Sumtiply
                .SumtiplicationOf(listOfInput1, 3)
                .ifPresentOrElse(System.out::println, printIfNotPresent(3));
    }

    private static Runnable printIfNotPresent(int number) {
        return () -> System.out.println("No Value Found for " + number + " complements to 2020");
    }
}
