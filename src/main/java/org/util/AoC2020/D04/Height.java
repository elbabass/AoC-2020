package org.util.AoC2020.D04;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Height {
    private final Number value;

    public Height(Number heightInCm) {
        this.value = heightInCm;
    }

    public Height(String heightString) {
        System.out.println("Height String : '" + heightString + "'");
        final Matcher matcher = Pattern
                .compile(("^(\\d+)"))
                .matcher(heightString);
        if (matcher.find()) {
            value = Integer.parseInt(matcher.group(1));
        }
        else {
            value = Integer.parseInt(heightString);
        }
    }

    public Number getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Height)) return false;
        Height height = (Height) o;
        return value.equals(height.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
