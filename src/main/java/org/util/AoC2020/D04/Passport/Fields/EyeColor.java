package org.util.AoC2020.D04.Passport.Fields;

import java.util.Arrays;

public class EyeColor extends PassportField<String> {
    public static final String[] validEyeColors = new String[]{"amb", "blu", "brn", "gry", "grn", "hzl", "oth"};

    public EyeColor(String hexadecimalColor) {
        super(hexadecimalColor);
    }

    public EyeColor() {
        super();
    }

    @Override
    public String getKeyName() {
        return EYE_COLOR_KEY;
    }

    @Override
    public boolean isValid() {
        return (value != null)
                && Arrays.asList(validEyeColors).contains(value);
    }

    public static PassportField<String> of(String eyeColor) {
        return new EyeColor(eyeColor) {
        };
    }
}
