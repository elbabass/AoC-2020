package D02;

import D02.configurator.ValidPasswordPolicy;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import org.apache.commons.lang3.StringUtils;
import org.javatuples.Pair;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.util.AoC2020.D02.PasswordPolicy;
import org.util.AoC2020.D02.PasswordValidation;
import org.util.AoC2020.Helpers;

import java.util.List;

public class PasswordValidationTest {
    @Test
    void PasswordPolicyApplies() {
        final PasswordPolicy passwordPolicy = new PasswordPolicy(1, 3, 'a');
        final String password = "abcde";
        final boolean passwordIsValid = PasswordValidation.validate(passwordPolicy, password);
        Assert.assertTrue(passwordIsValid);
    }

    @Property
    void GeneratePasswordWithMinPolicyCharacters(
            @ForAll @ValidPasswordPolicy PasswordPolicy passwordPolicy
    ) {
        String password = StringUtils.repeat(passwordPolicy.getCharacter(), passwordPolicy.getRangeMin());
        Assert.assertTrue(PasswordValidation.validate(passwordPolicy, password));
    }

    @Property
    void GeneratePasswordWithMaxPolicyCharacters(
            @ForAll @ValidPasswordPolicy PasswordPolicy passwordPolicy
    ) {
        String password = StringUtils.repeat(passwordPolicy.getCharacter(), passwordPolicy.getRangeMax());
        Assert.assertTrue(PasswordValidation.validate(passwordPolicy, password));
    }

    @Property
    void GeneratePasswordWithMoreThanMaxPolicyCharacters(
            @ForAll @ValidPasswordPolicy PasswordPolicy passwordPolicy
    ) {
        String password = StringUtils.repeat(passwordPolicy.getCharacter(), passwordPolicy.getRangeMax() + 1);
        Assert.assertFalse(PasswordValidation.validate(passwordPolicy, password));
    }

    @Property
    void GeneratePasswordWithLessThanMinPolicyCharacters(
            @ForAll @ValidPasswordPolicy PasswordPolicy passwordPolicy
    ) {
        String password = StringUtils.repeat(passwordPolicy.getCharacter(), passwordPolicy.getRangeMin() - 1);
        Assert.assertFalse(PasswordValidation.validate(passwordPolicy, password));
    }

    @Test
    void ThereAreExactly2ValidPasswordsInThisList() {
        final long expected = 2;
        List<Pair<PasswordPolicy, String>> passwordsToCheck = Helpers.getConvertedPolicyAndPasswordInputs(
                "d02-tests.txt",
                Helpers::passwordPolicyAndStringFromEntry
        );
        Assert.assertEquals(expected, PasswordValidation.countFromList(passwordsToCheck));
    }
}