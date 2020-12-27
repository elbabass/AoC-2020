package D02;

import D02.configurator.ValidPasswordPolicy;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.LowerChars;
import net.jqwik.api.constraints.StringLength;
import org.apache.commons.lang3.StringUtils;
import org.javatuples.Pair;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.util.AoC2020.D02.*;
import org.util.AoC2020.Helpers;

import java.util.List;

public class PasswordValidationTest {

    private static final int MIN = 1;
    private static final int MAX = 2;
    private static final int BOTH = 3;
    private final List<Pair<PasswordPolicy, String>> passwordsToCheck = Helpers.getConvertedPolicyAndPasswordInputs(
            "d02-tests.txt",
            Helpers::passwordPolicyAndStringFromEntry
    );

    @Test
    void PasswordPolicyWithInjectedRules() {
        final PasswordPolicy passwordPolicy = new PasswordPolicy(1, 3, 'a');
        final String password = "abcde";
        final PasswordRule passwordRule = new PasswordRuleWithRange();
        final boolean passwordIsValid = PasswordValidation.validate(passwordPolicy, password, passwordRule);
        Assertions.assertTrue(passwordIsValid);
    }

    @Property
    void GeneratePasswordWithMinPolicyCharacters(
            @ForAll @ValidPasswordPolicy PasswordPolicy passwordPolicy
    ) {
        String password = StringUtils.repeat(passwordPolicy.getCharacter(), passwordPolicy.getRangeMin());
        Assertions.assertTrue(PasswordValidation.validate(passwordPolicy, password, new PasswordRuleWithRange()));
    }

    @Property
    void GeneratePasswordWithMaxPolicyCharacters(
            @ForAll @ValidPasswordPolicy PasswordPolicy passwordPolicy
    ) {
        String password = StringUtils.repeat(passwordPolicy.getCharacter(), passwordPolicy.getRangeMax());
        Assertions.assertTrue(PasswordValidation.validate(passwordPolicy, password, new PasswordRuleWithRange()));
    }

    @Property
    void GeneratePasswordWithMoreThanMaxPolicyCharacters(
            @ForAll @ValidPasswordPolicy PasswordPolicy passwordPolicy
    ) {
        String password = StringUtils.repeat(passwordPolicy.getCharacter(), passwordPolicy.getRangeMax() + 1);
        Assertions.assertFalse(PasswordValidation.validate(passwordPolicy, password, new PasswordRuleWithRange()));
    }

    @Property
    void GeneratePasswordWithLessThanMinPolicyCharacters(
            @ForAll @ValidPasswordPolicy PasswordPolicy passwordPolicy
    ) {
        String password = StringUtils.repeat(passwordPolicy.getCharacter(), passwordPolicy.getRangeMin() - 1);
        Assertions.assertFalse(PasswordValidation.validate(passwordPolicy, password, new PasswordRuleWithRange()));
    }

    @Test
    void ThereAreExactly2ValidPasswordsInThisListWithInjectedRule() {
        final long expected = 2;
        final PasswordRule passwordRule = new PasswordRuleWithRange();
        Assertions.assertEquals(expected, PasswordValidation.countFromList(passwordsToCheck, passwordRule));
    }

    @Property
    void GeneratedPasswordWithMinPositionRules(
            @ForAll @ValidPasswordPolicy PasswordPolicy passwordPolicy,
            @ForAll @LowerChars @StringLength(value = 10) String passwordBase
    ) {
        assertPasswordIsValidWithValue(passwordPolicy, passwordBase, MIN);
    }

    @Property
    void GeneratedPasswordWithMaxPositionRules(
            @ForAll @ValidPasswordPolicy PasswordPolicy passwordPolicy,
            @ForAll @LowerChars @StringLength(value = 10) String passwordBase
    ) {
        assertPasswordIsValidWithValue(passwordPolicy, passwordBase, MAX);
    }

    private void assertPasswordIsValidWithValue(@ForAll @ValidPasswordPolicy PasswordPolicy passwordPolicy, @ForAll @LowerChars @StringLength(value = 10) String passwordBase, int edge) {
        String password = generatePassword(passwordPolicy, passwordBase, edge);
        Assertions.assertTrue(PasswordValidation.validate(passwordPolicy, password, new PasswordRuleWithPositions()));
    }

    @Property
    void GeneratedPasswordWithBothPositionRules(
            @ForAll @ValidPasswordPolicy PasswordPolicy passwordPolicy,
            @ForAll @LowerChars @StringLength(value = 10) String passwordBase
    ) {
        String password = generatePassword(passwordPolicy, passwordBase, BOTH);
        Assertions.assertFalse(
                PasswordValidation.validate(
                        passwordPolicy,
                        password, new PasswordRuleWithPositions()));
    }

    @NotNull
    private String generatePassword(PasswordPolicy passwordPolicy, String passwordBase, int putCharacterPolicy) {
        final char policyCharacter = passwordPolicy.getCharacter();
        char firstChar = Character.toUpperCase(policyCharacter);
        char lastChar = Character.toUpperCase(policyCharacter);

        lastChar = getSpecificChar(putCharacterPolicy, policyCharacter, lastChar, MAX);
        firstChar = getSpecificChar(putCharacterPolicy, policyCharacter, firstChar, MIN);
        return passwordBase.substring(0, passwordPolicy.getRangeMin() - 1)
                + firstChar
                + passwordBase.substring(passwordPolicy.getRangeMin(), passwordPolicy.getRangeMax() - 1)
                + lastChar
                + passwordBase.substring(passwordPolicy.getRangeMax());
    }

    private char getSpecificChar(int putCharacterPolicy, char policyCharacter, char defaultChar, int edge) {
        if ((putCharacterPolicy == edge) || (putCharacterPolicy == BOTH)) {
            return policyCharacter;
        }
        return defaultChar;
    }
}