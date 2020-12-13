package org.util.AoC2020.D02;

import org.javatuples.Pair;

import java.util.List;

public class PasswordValidation {
    public static boolean validate(PasswordPolicy passwordPolicy, String password) {
        final long countRequiredCharacter = password.chars().filter((c) -> c == passwordPolicy.getCharacter()).count();
        return countRequiredCharacter >= passwordPolicy.getRangeMin()
                && countRequiredCharacter <= passwordPolicy.getRangeMax();
    }

    public static long countFromList(List<Pair<PasswordPolicy, String>> passwordsToCheck) {
        return passwordsToCheck.stream().filter(PasswordValidation::validate).count();
    }

    private static boolean validate(Pair<PasswordPolicy, String> passwordPolicyStringPair) {
        return validate(passwordPolicyStringPair.getValue0(), passwordPolicyStringPair.getValue1());
    }
}
