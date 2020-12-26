package org.util.AoC2020.D04;

import java.util.Arrays;
import java.util.List;

import static org.util.AoC2020.D04.Passport.*;

public class PassportValidation {
    private static final String[] requiredFields = new String[]{
            BIRTH_YEAR_KEY,
            ISSUE_YEAR_KEY,
            EXPIRATION_YEAR_KEY,
            HEIGHT_KEY,
            HAIR_COLOR_KEY,
            EYE_COLOR_KEY,
            PASSWORD_ID_KEY,
    };
    public static boolean isValid(Passport passport) {
        return Arrays.stream(requiredFields).allMatch(passport::hasKey);
    }

    public static long countValidPassports(List<String> passportList) {
        return passportList.stream().filter(line -> {
            final Passport passport = new Passport(line);
            return isValid(passport);
        }).count();
    }
}
