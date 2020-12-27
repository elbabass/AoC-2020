package org.util.AoC2020.D04.Passport.Fields;

import java.util.Objects;

public abstract class PassportField<T> {
    public static final String BIRTH_YEAR_KEY = "byr";
    public static final String ISSUE_YEAR_KEY = "iyr";
    public static final String EXPIRATION_YEAR_KEY = "eyr";
    public static final String HEIGHT_KEY = "hgt";
    public static final String HAIR_COLOR_KEY = "hcl";
    public static final String EYE_COLOR_KEY = "ecl";
    public static final String PASSWORD_ID_KEY = "pid";
    public static final String COUNTRY_ID_KEY = "cid";

    protected final T value;
    protected final boolean assigned;

    protected PassportField() {
        value = null;
        assigned = false;
    }

    public PassportField(T assignment) {
        if (assignment != null) {
            value = assignment;
            assigned = true;
        } else {
            value = null;
            assigned = false;
        }
    }

    public abstract String getKeyName();

    public boolean hasValue() {
        return assigned;
    }

    public T getValue() {
        return value;
    }

    public String getPassportString() {
        return hasValue() ? getKeyName() + ":" + value : "";
    }

    public String toString() {
        return String.valueOf(value);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PassportField<?>)) return false;
        PassportField<?> passportField = (PassportField<?>) o;
        if (value != null) {
            return value.equals(passportField.value);
        }
        return passportField.value == null;
    }

    public int hashCode() {
        return Objects.hash(value);
    }
}
