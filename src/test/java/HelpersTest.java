import net.jqwik.api.*;
import net.jqwik.api.constraints.LowerChars;
import org.javatuples.Pair;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.util.AoC2020.D02.PasswordPolicy;
import org.util.AoC2020.Helpers;

import java.util.Arrays;
import java.util.List;

public class HelpersTest {
    final int[] baseList = {1721,
            979,
            366,
            299,
            675,
            1456};

    @Test
    void ListOfIntCanBeConvertedBack() {
        String baseString = """
                1721
                979
                366
                299
                675
                1456""";
        int[] actual = Helpers.listOfIntFromText(baseString);
        Assert.assertArrayEquals(baseList, actual);
    }

    @Test
    void ConvertedIntInputsFromFile() {
        int[] actual = Helpers.getConvertedIntInputs("d01-tests.txt", Integer::parseInt);
        Assert.assertArrayEquals(baseList, actual);
    }

    @Test
    void ConvertedPolicyAndPasswordInputsFromFile() {
        List<Pair<PasswordPolicy, String>> expected = Arrays.asList(
                Pair.with(new PasswordPolicy(1, 3, 'a'), "abcde"),
                Pair.with(new PasswordPolicy(1, 3, 'b'), "cdefg"),
                Pair.with(new PasswordPolicy(2, 9, 'c'), "ccccccccc")
        );

        List<Pair<PasswordPolicy, String>> actual = Helpers.getConvertedPolicyAndPasswordInputs(
                "d02-tests.txt",
                Helpers::passwordPolicyAndStringFromEntry
        );

        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Property
    void PoliciesAndPasswordCanBeConvertedBack(
            @ForAll PasswordPolicy passwordPolicy,
            @ForAll @LowerChars String password
    ) {
        Pair<PasswordPolicy, String> expected = Pair.with(passwordPolicy, password);
        String input = passwordPolicy.getRangeMin() + "-"
                + passwordPolicy.getRangeMax() + " "
                + passwordPolicy.getCharacter() + ": "
                + password;
        Pair<PasswordPolicy, String> actual = Helpers.passwordPolicyAndStringFromEntry(input);
        Assert.assertEquals(expected.getValue0().getCharacter(), actual.getValue0().getCharacter());
        Assert.assertEquals(expected.getValue0().getRangeMax(), actual.getValue0().getRangeMax());
        Assert.assertEquals(expected.getValue0().getRangeMin(), actual.getValue0().getRangeMin());
        Assert.assertEquals(expected.getValue1(), actual.getValue1());
    }
}