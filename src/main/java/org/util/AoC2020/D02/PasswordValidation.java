package org.util.AoC2020.D02;

import org.javatuples.Pair;

import java.util.List;

public class PasswordValidation {
    @Deprecated
    public static boolean validate(PasswordPolicy passwordPolicy, String password) {
        return validate(passwordPolicy, password, new PasswordRuleWithRange());
    }

    public static boolean validate(PasswordPolicy passwordPolicy, String password, PasswordRule passwordRule) {
        return passwordRule.validate(passwordPolicy, password);
    }

    private static boolean validate(Pair<PasswordPolicy, String> passwordPolicyStringPair, PasswordRule passwordRule) {
        return validate(passwordPolicyStringPair.getValue0(), passwordPolicyStringPair.getValue1(), passwordRule);
    }

    @Deprecated
    public static long countFromList(List<Pair<PasswordPolicy, String>> passwordsToCheck) {
        return countFromList(passwordsToCheck, new PasswordRuleWithRange());
    }

    public static long countFromList(List<Pair<PasswordPolicy, String>> passwordsToCheck, PasswordRule passwordRule) {
        return passwordsToCheck.stream().filter(passwordPolicyStringPair -> validate(passwordPolicyStringPair, passwordRule)).count();
    }
}
