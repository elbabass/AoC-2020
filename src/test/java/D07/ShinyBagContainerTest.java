package D07;

import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import org.util.AoC2020.D07.ShinyBagContainer;

import static org.assertj.core.api.Assertions.assertThat;

public class ShinyBagContainerTest {
    @Provide
    Arbitrary<String> shades() {
        return Arbitraries.of(
                "light",
                "dark",
                "bright",
                "muted",
                "shiny",
                "dark",
                "vibrant",
                "faded",
                "dotted"
        );
    }

    @Provide
    Arbitrary<String> color() {
        return Arbitraries.of(
                "red",
                "orange",
                "white",
                "yellow",
                "gold",
                "olive",
                "plum",
                "blue",
                "black"
        );
    }

    @Property
    void canCountBagContainingDirectlyShinyBags(
            @ForAll("shades") String shade,
            @ForAll("color") String color,
            @ForAll @IntRange(min = 2, max = 9) Integer number
            ){
        String entry = shade + " " + color + " contain " + number + " shiny bags";
        long expected = 1L;
        long actual = ShinyBagContainer.countShinyBagsContainers(entry);
        assertThat(expected).isEqualTo(actual);
    }

    @Property
    void canCountBagContainingDirectly1ShinyBag(
            @ForAll("shades") String shade,
            @ForAll("color") String color
    ){
        String entry = shade + " " + color + " contain 1 shiny bag";
        long expected = 1L;
        long actual = ShinyBagContainer.countShinyBagsContainers(entry);
        assertThat(expected).isEqualTo(actual);
    }

    @Property
    void canCountBagContainingNoShinyBag(
            @ForAll("shades") String shade,
            @ForAll("color") String color,
            @ForAll @IntRange(min = 2, max = 9) Integer number
    ){
        String entry = shade + " " + color + " contain " + number + " blue bags";
        long expected = 0L;
        long actual = ShinyBagContainer.countShinyBagsContainers(entry);
        assertThat(expected).isEqualTo(actual);
    }

}
