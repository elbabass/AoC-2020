package org.util.AoC2020.D02;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof PasswordPolicy)) {
            return false;
        }
        PasswordPolicy passwordPolicy = (PasswordPolicy) o;
        return (this.character == passwordPolicy.getCharacter()) &&
                (Objects.equals(this.rangeMin, passwordPolicy.getRangeMin())) &&
                (Objects.equals(this.rangeMax, passwordPolicy.getRangeMax()));
    }

    @Override
    public String toString() {
        return "" + this.rangeMin + "-" + this.rangeMax + " " + this.character;
    }
}
