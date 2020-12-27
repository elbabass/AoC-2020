package org.util.AoC2020.D04.Passport.Fields;

public abstract class YearPasswordField extends PassportField<Integer> {

    public YearPasswordField() {
        super();
    }

    public YearPasswordField(Integer assignment) {
        super(assignment);
    }

    protected boolean valueBetween(int min, int max) {
        return value != null && value >= min && value <= max;
    }

}
