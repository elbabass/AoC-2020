package org.util.AoC2020.D04.Passport.Fields;

public class BirthYear extends YearPasswordField {
    public BirthYear() {
        super();
    }

    public BirthYear(Integer assignment) {
        super(assignment);
    }

    public static PassportField<Integer> of(String value) {
        return new BirthYear(Integer.parseInt(value));
    }

    @Override
    public String getKeyName() {
        return BIRTH_YEAR_KEY;
    }

    @Override
    public boolean isValid() {
        return valueBetween(1920, 2002);
    }
}
