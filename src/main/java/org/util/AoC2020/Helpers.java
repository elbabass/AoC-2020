package org.util.AoC2020;

import io.reactivex.rxjava3.core.Maybe;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Helpers {
    public static int[] listOfIntFromText(@NotNull String str) {
        String[] strNums = str.split("\n");
        return Stream.of(strNums).mapToInt(Integer::parseInt).toArray();
    }

    public static Maybe<String> getEntry() {
        String entries;
        try {
            //noinspection BlockingMethodInNonBlockingContext
            entries = new String(Files.readAllBytes(Paths.get(Main.basePath)));
        } catch (Exception e) {
            return Maybe.error(e);
        }
        return Maybe.just(entries);
    }
}
