package D02;

import D02.configurator.ValidPasswordPolicy;
import net.jqwik.api.*;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.util.AoC2020.D02.PasswordPolicy;
import org.util.AoC2020.D02.PasswordValidation;

public class PasswordValidationTest {
//    List<Pair<PasswordPolicy, String>> actual = Helpers.getConvertedPolicyAndPasswordInputs(
//            "d02-tests.txt",
//            Helpers::passwordPolicyAndStringFromEntry
//    );

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
}