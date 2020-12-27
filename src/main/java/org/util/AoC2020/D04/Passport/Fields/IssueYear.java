package org.util.AoC2020.D04.Passport.Fields;

public class IssueYear extends YearPasswordField {
    public IssueYear() {
        super();
    }

    public IssueYear(Integer value) {
        super(value);
    }

    public static PassportField<Integer> of(String value) {
        return new IssueYear(Integer.parseInt(value));
    }

    @Override
    public String getKeyName() {
        return ISSUE_YEAR_KEY;
    }

    @Override
    public boolean isValid() {
        return valueBetween(2010, 2020);
    }
}
