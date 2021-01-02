package org.util.AoC2020.D07;

public class ShinyBagContainer {
    public static long countShinyBagsContainers(String entry) {
        return entry.contains("shiny bag") ? 1 : 0;
    }
}
