package org.util.AoC2020.D02;

public class PasswordRuleWithPositions implements PasswordRule {
    @Override
    public boolean validate(PasswordPolicy passwordPolicy, String password) {
        final boolean minCharacterOk = password.charAt(passwordPolicy.getRangeMin() - 1) == passwordPolicy.getCharacter();
        final boolean maxCharacterOk = password.charAt(passwordPolicy.getRangeMax() - 1) == passwordPolicy.getCharacter();
        return (minCharacterOk || maxCharacterOk)
                && !(minCharacterOk && maxCharacterOk);
    }
}
