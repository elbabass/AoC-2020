package org.util.AoC2020.D04;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.util.AoC2020.D04.PassportField.*;

public final class Passport {
    protected final BirthYear birthYear;
    protected final IssueYear issueYear;
    protected final ExpirationYear expirationYear;
    protected final Height height;
    protected final HairColor hairColor;
    protected final EyeColor eyeColor;
    protected final PassportId passportId;
    protected final CountryId countryId;

    public Passport() {
        birthYear = new BirthYear();
        issueYear = new IssueYear();
        expirationYear = new ExpirationYear();
        height = new Height();
        hairColor = new HairColor();
        eyeColor = new EyeColor();
        passportId = new PassportId();
        countryId = new CountryId();
    }

    public Passport(String stringifyPassword) {
        BirthYear birthYearField = new BirthYear();
        IssueYear issueYearField = new IssueYear();
        ExpirationYear expirationYearField = new ExpirationYear();
        Height heightField = new Height();
        HairColor hairColorField = new HairColor();
        EyeColor eyeColorField = new EyeColor();
        PassportId passportIdField = new PassportId();
        CountryId countryIdField = new CountryId();
        for (String stringField : stringifyPassword.split(" ")) {
            String[] keyValue = stringField.split(":");
            String key = keyValue[0];
            String value = keyValue[1];
            switch (key) {
                case BIRTH_YEAR_KEY -> birthYearField = (BirthYear) BirthYear.of(value);
                case ISSUE_YEAR_KEY -> issueYearField = (IssueYear) IssueYear.of(value);
                case EXPIRATION_YEAR_KEY -> expirationYearField = (ExpirationYear) ExpirationYear.of(value);
                case HEIGHT_KEY -> heightField = (Height) Height.of(value);
                case HAIR_COLOR_KEY -> hairColorField = (HairColor) HairColor.of(value);
                case EYE_COLOR_KEY -> eyeColorField = (EyeColor) EyeColor.of(value);
                case PASSWORD_ID_KEY -> passportIdField = (PassportId) PassportId.of(value);
                case COUNTRY_ID_KEY -> countryIdField = (CountryId) CountryId.of(value);
                default -> System.out.println("Field not found : " + key);
            }
        }
        birthYear = birthYearField;
        issueYear = issueYearField;
        expirationYear = expirationYearField;
        height = heightField;
        hairColor = hairColorField;
        eyeColor = eyeColorField;
        passportId = passportIdField;
        countryId = countryIdField;
    }

    public Passport(PassportField<?> ...fields) {
        BirthYear birthYearField = new BirthYear();
        IssueYear issueYearField = new IssueYear();
        ExpirationYear expirationYearField = new ExpirationYear();
        Height heightField = new Height();
        HairColor hairColorField = new HairColor();
        EyeColor eyeColorField = new EyeColor();
        PassportId passportIdField = new PassportId();
        CountryId countryIdField = new CountryId();
        for (PassportField<?> passportField : fields) {
            switch (passportField.getKeyName()) {
                case BIRTH_YEAR_KEY -> birthYearField = new BirthYear((Integer) passportField.getValue());
                case ISSUE_YEAR_KEY -> issueYearField = new IssueYear((Integer) passportField.getValue());
                case EXPIRATION_YEAR_KEY -> expirationYearField = new ExpirationYear((Integer) passportField.getValue());
                case HEIGHT_KEY -> heightField = new Height((Number) passportField.getValue());
                case HAIR_COLOR_KEY -> hairColorField = new HairColor((String) passportField.getValue());
                case EYE_COLOR_KEY -> eyeColorField = new EyeColor((String) passportField.getValue());
                case PASSWORD_ID_KEY -> passportIdField = new PassportId((String) passportField.getValue());
                case COUNTRY_ID_KEY -> countryIdField = new CountryId((Integer) passportField.getValue());
                default -> System.out.println("Field not found : " + passportField.getKeyName());
            }
        }
        birthYear = birthYearField;
        issueYear = issueYearField;
        expirationYear = expirationYearField;
        height = heightField;
        hairColor = hairColorField;
        eyeColor = eyeColorField;
        passportId = passportIdField;
        countryId = countryIdField;
    }

    public static Passport copyOf(Passport passport) {
        return new Passport(passport.getFields());
    }

    public PassportField<?>[] getFields() {
        return new PassportField<?>[]{
                birthYear,
                issueYear,
                expirationYear,
                height,
                hairColor,
                eyeColor,
                passportId,
                countryId
        };
    }

    public boolean hasKey(String keyCode) {
        return switch (keyCode) {
            case BIRTH_YEAR_KEY -> birthYear.hasValue();
            case ISSUE_YEAR_KEY -> issueYear.hasValue();
            case EXPIRATION_YEAR_KEY -> expirationYear.hasValue();
            case HEIGHT_KEY -> height.hasValue();
            case HAIR_COLOR_KEY -> hairColor.hasValue();
            case EYE_COLOR_KEY -> eyeColor.hasValue();
            case PASSWORD_ID_KEY -> passportId.hasValue();
            case COUNTRY_ID_KEY -> countryId.hasValue();
            default -> false;
        };
    }

    public String toString() {
        return Arrays.stream(getFields())
                .filter(PassportField::hasValue)
                .map(PassportField::getPassportString)
                .collect(Collectors.joining(" "));
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null) return false;
        if (!(o instanceof Passport)) return false;
        Passport otherPassport = (Passport) o;
        return Arrays.equals(getFields(), otherPassport.getFields());
    }
}
