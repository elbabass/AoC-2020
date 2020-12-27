package D02;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.util.AoC2020.D02.PasswordPolicy;

public class PasswordPolicyTest {
    @Test
    void ToSting() {
        String expected = "1-2 a";
        PasswordPolicy passwordPolicy = new PasswordPolicy(1, 2, 'a');
        Assertions.assertEquals(expected, passwordPolicy.toString());
    }
}
