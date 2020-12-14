package org.util.AoC2020.D01;

import org.util.AoC2020.Helpers;

import java.util.Optional;

public class Main {
    static final String basePath = "d01-inputs.txt";
    public static void main(String[] args) {
        //noinspection deprecation
        Helpers.getEntry(basePath)
                .doOnSuccess((str) -> {
                    int[] listOfInput = Helpers.listOfIntFromText(str);
                    Optional<Integer> output2 = sumtiplyN(listOfInput, 2);
                    output2.ifPresentOrElse(System.out::println, () -> System.out.println("No Value Found for 2 complements to 2020"));
                    Optional<Integer> output3 = sumtiplyN(listOfInput, 3);
                    output3.ifPresentOrElse(System.out::println, () -> System.out.println("No Value Found for 3 complements to 2020"));
                })
                .doOnError((e) -> System.out.println("There was an error while loading file : " + e.toString()))
                .subscribe();
    }

    private static Optional<Integer> sumtiplyN(int[] listOfInput, int n) {
        return Sumtiply.SumtiplicationOf(listOfInput, n);
    }
}
