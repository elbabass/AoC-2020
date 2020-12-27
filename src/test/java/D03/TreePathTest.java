package D03;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.util.AoC2020.D03.TreePath;
import org.util.AoC2020.Helpers;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TreePathTest {
    private final List<char[]> mapPatterns =
            Objects.requireNonNull(Helpers.streamedStringsFromFile("d03-tests.txt"))
                    .map(String::toCharArray)
                    .collect(Collectors.toList());

    @Property
    void CanGetFurtherOnTheRight(
            @ForAll @IntRange(min = 1, max = 2000) int right
    ) {
        char expected = (right % 2 == 0) ? '.' : '#';
        char[] row = {'.', '#'};
        final char actual = TreePath.treeOrNot(row, right);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void SlopeOf1Down3RightPathHas7Trees() {
        int expected = 7;
        TreePath treePath = new TreePath(mapPatterns);
        int actual = treePath.treesOnSlope(1, 3);
        Assertions.assertEquals(expected, actual);
    }
}
