package org.util.AoC2020.D04;

public class HairColor extends PassportField<String> {
    public HairColor(String hexadecimalColor) {
        super(hexadecimalColor);
    }

    public HairColor() {
        super();
    }

    @Override
    String getKeyName() {
        return HAIR_COLOR_KEY;
    }

    public static PassportField<String> of(String hexadecimalColor) {
        String hashTag = hexadecimalColor.startsWith("#")?"":"#";
        return new HairColor(hashTag + hexadecimalColor) {
        };
    }

}
