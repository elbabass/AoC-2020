package org.util.AoC2020;

import io.reactivex.rxjava3.core.Maybe;
import org.javatuples.Pair;
import org.jetbrains.annotations.NotNull;
import org.util.AoC2020.D02.PasswordPolicy;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Stack;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Helpers {
    private static final String inputBasePath = System.getProperty("user.dir") + File.separator + "inputs" + File.separator;

    public static int[] listOfIntFromText(@NotNull String str) {
        String[] strNums = str.split("\n");
        return Stream.of(strNums).mapToInt(Integer::parseInt).toArray();
    }

    @Deprecated
    public static Maybe<String> getEntry(String basePath) {
        String entries;
        try {
            //noinspection BlockingMethodInNonBlockingContext
            entries = new String(Files.readAllBytes(Paths.get(inputBasePath + basePath)));
        } catch (Exception e) {
            return Maybe.error(e);
        }
        return Maybe.just(entries);
    }

    public static Stream<String> streamedStringsFromFile(String fileName) {
        final Path path = Paths.get(inputBasePath + fileName);
        final Stream<String> lines;
        try {
            lines = Files.lines(path);
            return lines;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int[] getConvertedIntInputs(String fileName, ToIntFunction<? super String> mapper) {
        final Stream<String> lines = streamedStringsFromFile(fileName);
        assert lines != null;
        return lines.mapToInt(mapper).toArray();
    }

    public static List<Pair<PasswordPolicy, String>> getConvertedPolicyAndPasswordInputs(
            String fileName,
            Function<? super String, ? extends Pair<PasswordPolicy, String>> mapper
    ) {
        return Objects.requireNonNull(streamedStringsFromFile(fileName)).map(mapper).collect(Collectors.toList());
    }

    public static Pair<PasswordPolicy, String> passwordPolicyAndStringFromEntry(String input) {
        String[] policyAndPasswordStr = input.split(":");
        String[] policyRangeAndCharacter = policyAndPasswordStr[0].split(" ");
        String[] policyRange = policyRangeAndCharacter[0].split("-");
        PasswordPolicy passwordPolicy = new PasswordPolicy(
                Integer.parseInt(policyRange[0]),
                Integer.parseInt(policyRange[1]),
                policyRangeAndCharacter[1].charAt(0));
        return Pair.with(passwordPolicy, policyAndPasswordStr[1].trim());
    }

    public static List<String> parseParagraphsAsStrings(String filename) {
        StringBuilder currentPassport = new StringBuilder();
        Stack<String> passports = new Stack<>();
        String separator = "";
        for (String line : Objects.requireNonNull(streamedStringsFromFile(filename)).collect(Collectors.toList())) {
            if (line.isEmpty()) {
                passports.add(currentPassport.toString());
                currentPassport = new StringBuilder();
                separator = "";
            }
            else {
                currentPassport.append(separator).append(line);
                separator = " ";
            }
        }
        if (!currentPassport.isEmpty()) {
            passports.add(currentPassport.toString());
        }
        return passports;
    }
}
