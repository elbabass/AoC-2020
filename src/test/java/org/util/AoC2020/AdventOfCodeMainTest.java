package org.util.AoC2020;

import net.jqwik.api.Example;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;

import static org.assertj.core.api.Assertions.assertThat;

class Day33Main extends AdventOfCodeMain {
    public static String theStaticMethod() {
        return currentClassGetter.getSimpleClassName();
    }

    public static String theStaticMethodLong() {
        return currentClassGetter.getClassName();
    }
}

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

    @Example
    void getSimpleClassName_OfStaticMethod_returnsChildClassName() {
        final String expected = "Day33Main";
        final String actual = Day33Main.theStaticMethod();
        assertThat(expected).isEqualTo(actual);
    }

    @Example
    void getClassName_OfStaticMethod_returnsFullChildClassName() {
        final String expected = "org.util.AoC2020.Day33Main";
        final String actual = Day33Main.theStaticMethodLong();
        assertThat(expected).isEqualTo(actual);
    }
}