package D07;

import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import org.util.AoC2020.D07.ShinyBagContainer;

import static org.assertj.core.api.Assertions.assertThat;

public class ShinyBagContainerTest {
    private final String[] shades = {"light",
            "dark",
            "bright",
            "muted",
            "shiny",
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
    Arbitrary<String> color() {
        return Arbitraries.of(colors);
    }

    @Property
    void canCountBagContainingDirectlyShinyBags(
            @ForAll("shades") String shade,
            @ForAll("color") String color,
            @ForAll @IntRange(min = 1, max = 9) Integer number
    ) {
        String entry = getSingleEntry(shade, color, number, " shiny bag");
        long expected = 1L;
        long actual = ShinyBagContainer.countShinyBagsContainers(entry);
        assertThat(expected).isEqualTo(actual);
    }

    @Property
    void canCountBagContainingNoShinyBag(
            @ForAll("shades") String shade,
            @ForAll("color") String color,
            @ForAll @IntRange(min = 1, max = 9) Integer number
    ) {
        String entry = getSingleEntry(shade, color, number, " blue bag");
        long expected = 0L;
        long actual = ShinyBagContainer.countShinyBagsContainers(entry);
        assertThat(expected).isEqualTo(actual);
    }

    private String getSingleEntry(@ForAll("shades") String shade, @ForAll("color") String color, @ForAll @IntRange(min = 2, max = 9) Integer number, String innerBag) {
        return shade + " " + color + " contain " + number + innerBag + ((number > 1) ? "s" : "");
    }

}
