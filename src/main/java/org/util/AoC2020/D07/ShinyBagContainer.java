package org.util.AoC2020.D07;

import org.javatuples.Pair;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShinyBagContainer {
    private final List<String> entries;
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private final Map<String, CanContainBag> containers;
    private final Pattern pattern = Pattern.compile("(\\w+ \\w+) bags contain (\\d) ([\\w ]+) bag(s)?");

    public ShinyBagContainer(List<String> entries) {
        this.entries = entries;
        containers = new HashMap<>() {{
            entries.stream()
                    .map(entry -> getCanContainBagPair(entry))
                    .filter(result -> result != null && this.put(result.getValue0(), result.getValue1()) != null)
                    .map(result -> "Duplicate key : old = " + this.get(result.getValue0()) + ", new = " + result.getValue1()).forEach(System.out::println);

        }};
    }

    public long countShinyBagsContainers() {
        return entries.stream().filter(this::containsShinyBag).count();
    }

    private Pair<String, CanContainBag> getCanContainBagPair(String entry) {
        Matcher matcher = pattern.matcher(entry);
        if (matcher.matches()) {
            CanContainBag canContainBag = new CanContainBag(matcher.group(3), Long.parseLong(matcher.group(2)));
            return Pair.with(matcher.group(1), canContainBag);
        }
        return null;
    }

    private boolean containsShinyBag(String entry) {
        boolean containerContains = false;
        final Pair<String, CanContainBag> canContainBagPair = getCanContainBagPair(entry);
        if (canContainBagPair != null && containers.containsKey(canContainBagPair.getValue0())) {
            final String bagType = canContainBagPair.getValue1().bagType;
            containerContains = containers.containsKey(bagType) && containers.get(bagType).bagType.contains("shiny");
        }
        return entry.contains("shiny bag")
                || containerContains;
    }

}
