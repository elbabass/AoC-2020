package org.util.AoC2020.D04.Passport.Fields;

public class ExpirationYear extends PassportField<Integer> {
    public ExpirationYear() {
        super();
    }

    public ExpirationYear(Integer value) {
        super(value);
    }

    public static PassportField<Integer> of(String value) {
        return new ExpirationYear(Integer.parseInt(value));
    }

    @Override
    public String getKeyName() {
        return EXPIRATION_YEAR_KEY;
    }
}
