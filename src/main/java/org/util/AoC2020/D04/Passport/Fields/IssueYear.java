package org.util.AoC2020.D04.Passport.Fields;

public class IssueYear extends PassportField<Integer> {
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
}
