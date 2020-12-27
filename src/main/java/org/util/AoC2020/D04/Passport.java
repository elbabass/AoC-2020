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
        MutablePassport mutablePassport = new MutablePassport();
        Arrays.stream(stringifyPassword.split(" "))
                .map(stringField -> stringField.split(":"))
                .forEach(mutablePassport::assignFieldsFromKeyValueStrings);
        birthYear = mutablePassport.birthYear;
        issueYear = mutablePassport.issueYear;
        expirationYear = mutablePassport.expirationYear;
        height = mutablePassport.height;
        hairColor = mutablePassport.hairColor;
        eyeColor = mutablePassport.eyeColor;
        passportId = mutablePassport.passportId;
        countryId = mutablePassport.countryId;
    }

    public Passport(PassportField<?>... fields) {
        MutablePassport mutablePassport = new MutablePassport();
        mutablePassport.fromFields(fields);
        birthYear = mutablePassport.birthYear;
        issueYear = mutablePassport.issueYear;
        expirationYear = mutablePassport.expirationYear;
        height = mutablePassport.height;
        hairColor = mutablePassport.hairColor;
        eyeColor = mutablePassport.eyeColor;
        passportId = mutablePassport.passportId;
        countryId = mutablePassport.countryId;
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

    public PassportField<?> getField(String keyCode) {
        return switch (keyCode) {
            case BIRTH_YEAR_KEY -> birthYear;
            case ISSUE_YEAR_KEY -> issueYear;
            case EXPIRATION_YEAR_KEY -> expirationYear;
            case HEIGHT_KEY -> height;
            case HAIR_COLOR_KEY -> hairColor;
            case EYE_COLOR_KEY -> eyeColor;
            case PASSWORD_ID_KEY -> passportId;
            case COUNTRY_ID_KEY -> countryId;
            default -> throw new NoSuchFieldError("Field '" + keyCode + "' can't be found");
        };
    }

    public boolean hasKey(String keyCode) {
        return getField(keyCode).hasValue();
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
