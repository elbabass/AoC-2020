package org.util.AoC2020.D02;

public class PasswordRuleWithRange implements PasswordRule {
    public boolean validate(PasswordPolicy passwordPolicy, String password) {
        final long countRequiredCharacter = password.chars().filter((c) -> c == passwordPolicy.getCharacter()).count();
        return countRequiredCharacter >= passwordPolicy.getRangeMin()
                && countRequiredCharacter <= passwordPolicy.getRangeMax();
    }
}
