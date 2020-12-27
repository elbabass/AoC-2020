package org.util.AoC2020.D04;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    String getKeyName() {
        return PASSWORD_ID_KEY;
    }
}
