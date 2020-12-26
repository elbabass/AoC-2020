package org.util.AoC2020.D04;

import java.util.Objects;

public class HexadecimalColor {
    private final String value;

    public HexadecimalColor(String hexadecimalColor) {
        String hashTag = hexadecimalColor.startsWith("#")?"":"#";
        this.value = hashTag + hexadecimalColor;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HexadecimalColor)) return false;
        HexadecimalColor that = (HexadecimalColor) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
