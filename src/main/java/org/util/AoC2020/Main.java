package org.util.AoC2020;

import java.io.File;

public class Main {
    static final String basePath = System.getProperty("user.dir") + File.separator + "entries.txt";
    public static void main(String[] args) {
        Helpers.getEntry()
                .doOnSuccess((str) -> {
                    int output = Sumtiply.Sumtiplication(Helpers.listOfIntFromText(str));
                    System.out.println("Output is " + output);
                })
                .doOnError((e) -> System.out.println("There was an error while loading file : " + e.toString()))
                .subscribe();
    }
}
