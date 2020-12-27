package org.util.AoC2020.D04;

public class EyeColor extends PassportField<String> {
    public EyeColor(String hexadecimalColor) {
        super(hexadecimalColor);
    }

    public EyeColor() {
        super();
    }

    @Override
    String getKeyName() {
        return EYE_COLOR_KEY;
    }

    public static PassportField<String> of(String hexadecimalColor) {
        String hashTag = hexadecimalColor.startsWith("#")?"":"#";
        return new EyeColor(hashTag + hexadecimalColor) {
        };
    }
}
