import net.jqwik.api.Example;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import org.junit.jupiter.api.Assertions;
import org.util.AoC2020.AdventOfCodeMain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AdventOfCodeMainTest {
    @Property
    void getDailyInputFile_WithNumberLowerThan10_ReturnsZeroPrefixedInName(
            @ForAll @IntRange(min = 1, max = 9) Integer day
    ) {
        final String expected = "d0" + day + "-inputs.txt";
        String input = AdventOfCodeMain.getDailyInputFile(day);
        assertThat(expected).isEqualTo(input);
    }

    @Property
    void getDailyInputFile_WithNumberHigherThan10_ReturnsFullNumberInName(
            @ForAll @IntRange(min = 10, max = 24) Integer day
    ) {
        final String expected = "d" + day + "-inputs.txt";
        String input = AdventOfCodeMain.getDailyInputFile(day);
        assertThat(expected).isEqualTo(input);
    }

}