package D07;

import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;
import net.jqwik.api.constraints.Unique;
import org.util.AoC2020.D07.ShinyBagContainer;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class ShinyBagContainerTest {
    private final String[] shades = {"light",
            "dark",
            "bright",
            "muted",
//            "shiny",
            "dark",
            "vibrant",
            "faded",
            "dotted"};
    private final String[] colors = {"red",
            "orange",
            "white",
            "yellow",
            "gold",
            "olive",
            "plum",
            "blue",
            "black"};

    @Provide
    Arbitrary<String> shades() {
        return Arbitraries.of(shades);
    }

    @Provide
    Arbitrary<String> colors() {
        return Arbitraries.of(colors);
    }

    @Property
    void canCountBagContainingDirectlyShinyBags(
            @ForAll("shades") String shade,
            @ForAll("colors") String color,
            @ForAll @IntRange(min = 1, max = 9) Integer number
    ) {
        String entry = getSingleEntry(shade, color, number, "shiny");
        long expected = 1L;
        long actual = new ShinyBagContainer(List.of(entry)).containers.countShinyBagsContainers();
        assertThat(expected).isEqualTo(actual);
    }

    @Property
    void canCountBagContainingNoShinyBag(
            @ForAll("shades") String shade,
            @ForAll("colors") String color,
            @ForAll @IntRange(min = 1, max = 9) Integer number
    ) {
        String entry = getSingleEntry(shade, color, number, "blue");
        long expected = 0L;
        long actual = new ShinyBagContainer(List.of(entry)).containers.countShinyBagsContainers();
        assertThat(expected).isEqualTo(actual);
    }

    @Property
    void canCountSeveralLinesOfDirectShinyBags(
            @ForAll @Size(value = 5) List<@From("shades") @Unique String> shades,
            @ForAll @Size(value = 5) List<@From("colors") @Unique String> colors,
            @ForAll @Size(value = 5) List<@IntRange(min = 1, max = 9) Integer> numbers
    ) {
        List<String> entries = IntStream
                .range(0, shades.size())
                .mapToObj(i -> getSingleEntry(shades.get(i), colors.get(i), numbers.get(i), "shiny"))
                .collect(Collectors.toList());
        long expected = 5L;
        long actual = new ShinyBagContainer(entries).containers.countShinyBagsContainers();
        assertThat(expected).isEqualTo(actual);
    }

    @Property
    void canCountBagContainingShinyBagContainer(
            @ForAll("shades") String shade,
            @ForAll("colors") String color,
            @ForAll @IntRange(min = 1, max = 9) Integer number
    ) {
        List<String> entries = List.of(
                getSingleEntry(shade, color, number, "shiny"),
                getSingleEntry("shadeOuter", "colorOuter", 1, shade + " " + color)
        );

        long expected = 2L;
        long actual = new ShinyBagContainer(entries).containers.countShinyBagsContainers();
        assertThat(expected).isEqualTo(actual);
    }

    private String getSingleEntry(@ForAll("shades") String shade, @ForAll("color") String color, @ForAll @IntRange(min = 2, max = 9) Integer number, String innerBag) {
        return shade + " " + color + " bags contain "
                + number + " " + innerBag + " bag" + ((number > 1) ? "s" : "");
    }

}
