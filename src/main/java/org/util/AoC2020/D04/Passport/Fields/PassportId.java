package org.util.AoC2020.D04.Passport.Fields;

public class PassportId extends PassportField<String> {
    public PassportId() {
        super();
    }

    public PassportId(String assignment) {
        super(assignment);
    }

    public static PassportField<String> of(String passportIdString) {
        return new PassportId(passportIdString);
    }

    @Override
    public String getKeyName() {
        return PASSWORD_ID_KEY;
    }

    @Override
    public boolean isValid() {
        return value != null && value.matches("\\d{9}");
    }
}
