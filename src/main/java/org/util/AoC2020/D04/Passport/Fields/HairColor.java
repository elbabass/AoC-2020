package org.util.AoC2020.D04.Passport.Fields;

public class HairColor extends PassportField<String> {
    public HairColor(String hexadecimalColor) {
        super(hexadecimalColor);
    }

    public HairColor() {
        super();
    }

    @Override
    public String getKeyName() {
        return HAIR_COLOR_KEY;
    }

    @Override
    public boolean isValid() {
        return value != null
                && value.matches("^#[a-f0-9]{6}$");
    }

    public static PassportField<String> of(String hexadecimalColor) {
        return new HairColor(hexadecimalColor) {
        };
    }

}
