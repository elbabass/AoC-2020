package D02;

import D02.configurator.ValidPasswordPolicy;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.LowerChars;
import net.jqwik.api.constraints.StringLength;
import org.apache.commons.lang3.StringUtils;
import org.javatuples.Pair;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
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
        Assert.assertTrue(passwordIsValid);
    }

    @Property
    void GeneratePasswordWithMinPolicyCharacters(
            @ForAll @ValidPasswordPolicy PasswordPolicy passwordPolicy
    ) {
        String password = StringUtils.repeat(passwordPolicy.getCharacter(), passwordPolicy.getRangeMin());
        Assert.assertTrue(PasswordValidation.validate(passwordPolicy, password, new PasswordRuleWithRange()));
    }

    @Property
    void GeneratePasswordWithMaxPolicyCharacters(
            @ForAll @ValidPasswordPolicy PasswordPolicy passwordPolicy
    ) {
        String password = StringUtils.repeat(passwordPolicy.getCharacter(), passwordPolicy.getRangeMax());
        Assert.assertTrue(PasswordValidation.validate(passwordPolicy, password, new PasswordRuleWithRange()));
    }

    @Property
    void GeneratePasswordWithMoreThanMaxPolicyCharacters(
            @ForAll @ValidPasswordPolicy PasswordPolicy passwordPolicy
    ) {
        String password = StringUtils.repeat(passwordPolicy.getCharacter(), passwordPolicy.getRangeMax() + 1);
        Assert.assertFalse(PasswordValidation.validate(passwordPolicy, password, new PasswordRuleWithRange()));
    }

    @Property
    void GeneratePasswordWithLessThanMinPolicyCharacters(
            @ForAll @ValidPasswordPolicy PasswordPolicy passwordPolicy
    ) {
        String password = StringUtils.repeat(passwordPolicy.getCharacter(), passwordPolicy.getRangeMin() - 1);
        Assert.assertFalse(PasswordValidation.validate(passwordPolicy, password, new PasswordRuleWithRange()));
    }

    @Test
    void ThereAreExactly2ValidPasswordsInThisListWithInjectedRule() {
        final long expected = 2;
        final PasswordRule passwordRule = new PasswordRuleWithRange();
        Assert.assertEquals(expected, PasswordValidation.countFromList(passwordsToCheck, passwordRule));
    }

    @Property
    void GeneratedPasswordWithMinPositionRules(
            @ForAll @ValidPasswordPolicy PasswordPolicy passwordPolicy,
            @ForAll @LowerChars @StringLength(value = 10) String passwordBase
    ) {
        String password = generatePassword(passwordPolicy, passwordBase, MIN);
        Assert.assertTrue(PasswordValidation.validate(passwordPolicy, password, new PasswordRuleWithPositions()));
    }

    @Property
    void GeneratedPasswordWithMaxPositionRules(
            @ForAll @ValidPasswordPolicy PasswordPolicy passwordPolicy,
            @ForAll @LowerChars @StringLength(value = 10) String passwordBase
    ) {
        String password = generatePassword(passwordPolicy, passwordBase, MAX);
        Assert.assertTrue(PasswordValidation.validate(passwordPolicy, password, new PasswordRuleWithPositions()));
    }

    @Property
    void GeneratedPasswordWithBothPositionRules(
            @ForAll @ValidPasswordPolicy PasswordPolicy passwordPolicy,
            @ForAll @LowerChars @StringLength(value = 10) String passwordBase
    ) {
        String password = generatePassword(passwordPolicy, passwordBase, BOTH);
        Assert.assertFalse(
                PasswordValidation.validate(
                        passwordPolicy,
                        password, new PasswordRuleWithPositions()));
    }

    @NotNull
    private String generatePassword(PasswordPolicy passwordPolicy, String passwordBase, int putCharacterPolicy) {
        final char policyCharacter = passwordPolicy.getCharacter();
        char firstChar = Character.toUpperCase(policyCharacter);
        char lastChar = Character.toUpperCase(policyCharacter);

        if ((putCharacterPolicy == MAX)||(putCharacterPolicy == BOTH)) {
            lastChar = policyCharacter;
        }
        if ((putCharacterPolicy == MIN)||(putCharacterPolicy == BOTH)) {
            firstChar = policyCharacter;
        }
        return passwordBase.substring(0, passwordPolicy.getRangeMin() - 1)
                + firstChar
                + passwordBase.substring(passwordPolicy.getRangeMin(), passwordPolicy.getRangeMax() - 1)
                + lastChar
                + passwordBase.substring(passwordPolicy.getRangeMax());
    }
}