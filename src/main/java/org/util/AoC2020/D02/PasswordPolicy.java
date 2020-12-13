package org.util.AoC2020.D02;

import org.jetbrains.annotations.NotNull;

public class PasswordPolicy {
    private final Integer rangeMin;
    private final Integer rangeMax;
    private final Character character;

    public PasswordPolicy(Integer rangeMin, Integer rangeMax, Character character) {
        this.rangeMin = rangeMin;
        this.rangeMax = rangeMax;
        this.character = character;
    }

    public Integer getRangeMin() {
        return rangeMin;
    }

    public Integer getRangeMax() {
        return rangeMax;
    }

    public Character getCharacter() {
        return character;
    }
}
