package org.util.AoC2020.D01;

import org.util.AoC2020.Helpers;

import java.util.Optional;

public class Day01Main {
    static final String basePath = "d01-inputs.txt";

    public static void main(String[] args) {
        int[] listOfInput1 = Helpers.getConvertedIntInputs(basePath, Integer::parseInt);
        Optional<Integer> output2 = sumtiplyN(listOfInput1, 2);
        output2.ifPresentOrElse(System.out::println, () -> System.out.println("No Value Found for 2 complements to 2020"));
        Optional<Integer> output3 = sumtiplyN(listOfInput1, 3);
        output3.ifPresentOrElse(System.out::println, () -> System.out.println("No Value Found for 3 complements to 2020"));
    }

    private static Optional<Integer> sumtiplyN(int[] listOfInput, int n) {
        return Sumtiply.SumtiplicationOf(listOfInput, n);
    }
}
