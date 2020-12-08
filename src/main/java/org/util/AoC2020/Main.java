package org.util.AoC2020;

import io.reactivex.rxjava3.core.Maybe;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class Main {
    static final String basePath = System.getProperty("user.dir") + File.separator + "entries.txt";
    public static void main(String[] args) {
        Maybe<String> entry = getEntry();
        int output = Sumtiply.Sumtiplication(Sumtiply.listOfIntFromText(Objects.requireNonNull(entry.blockingGet())));
        System.out.println("Output is " + output);

//        entry.doOnSuccess((str) -> {
//            int output = Sumtiply.Sumtiplication(Sumtiply.listOfIntFromText(str));
//            System.out.println("Output is " + output);
//        });
    }

    private static Maybe<String> getEntry() {
        String entries;
        try {
            //noinspection BlockingMethodInNonBlockingContext
            entries = new String(Files.readAllBytes(Paths.get(basePath)));
        } catch (Exception e) {
            return Maybe.error(e);
        }
        return Maybe.just(entries);
    }
}
