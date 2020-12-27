import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.LowerChars;
import org.javatuples.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.util.AoC2020.D02.PasswordPolicy;
import org.util.AoC2020.Helpers;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
        Assertions.assertArrayEquals(baseList, actual);
    }

    @Test
    void ConvertedIntInputsFromFile() {
        int[] actual = Helpers.getConvertedIntInputs("d01-tests.txt", Integer::parseInt);
        Assertions.assertArrayEquals(baseList, actual);
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

        Assertions.assertArrayEquals(expected.toArray(), actual.toArray());
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
        Assertions.assertEquals(expected.getValue0().getCharacter(), actual.getValue0().getCharacter());
        Assertions.assertEquals(expected.getValue0().getRangeMax(), actual.getValue0().getRangeMax());
        Assertions.assertEquals(expected.getValue0().getRangeMin(), actual.getValue0().getRangeMin());
        Assertions.assertEquals(expected.getValue1(), actual.getValue1());
    }

    @Test
    void StringLoaderAutomaton() {
        List<String> expected = Arrays.asList(
                "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd" +
                        " byr:1937 iyr:2017 cid:147 hgt:183cm",
                "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884" +
                        " hcl:#cfa07d byr:1929",
                "hcl:#ae17e1 iyr:2013" +
                        " eyr:2024" +
                        " ecl:brn pid:760753108 byr:1931" +
                        " hgt:179cm",
                "hcl:#cfa07d eyr:2025 pid:166559648 " +
                        "iyr:2011 ecl:brn hgt:59in");
        List<String> actual = Helpers.parseParagraphsAsStrings("d04-tests.txt");
        assertThat(expected).isEqualTo(actual);
    }
}