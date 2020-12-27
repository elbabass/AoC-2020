package org.util.AoC2020.D04;

import java.util.Arrays;
import java.util.Objects;

import static org.util.AoC2020.D04.PassportField.*;

public class Passport {
    private BirthYear birthYear;
    private IssueYear issueYear;
    private ExpirationYear expirationYear;
    private Height height;
    private HairColor hairColor;
    private EyeColor eyeColor;
    private PassportId passportId;
    private CountryId countryId;

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

    public static Passport of(String stringifyPassword) {
        Passport passport = new Passport();
        Arrays
                .stream(stringifyPassword.split(" "))
                .map((stringField) -> stringField.split(":"))
                .forEach(((keyValue) -> {
                    String key = keyValue[0];
                    String value = keyValue[1];
                    switch (key) {
                        case BIRTH_YEAR_KEY -> passport.birthYear = (BirthYear) BirthYear.of(value);
                        case ISSUE_YEAR_KEY -> passport.issueYear = (IssueYear) IssueYear.of(value);
                        case EXPIRATION_YEAR_KEY -> passport.expirationYear = (ExpirationYear) ExpirationYear.of(value);
                        case HEIGHT_KEY -> passport.height = (Height) Height.of(value);
                        case HAIR_COLOR_KEY -> passport.hairColor = (HairColor) HairColor.of(value);
                        case EYE_COLOR_KEY -> passport.eyeColor = (EyeColor) EyeColor.of(value);
                        case PASSWORD_ID_KEY -> passport.passportId = (PassportId) PassportId.of(value);
                        case COUNTRY_ID_KEY -> passport.countryId = (CountryId) CountryId.of(value);
                        default -> System.out.println("Field not found : " + key);
                    }
                }));
        return passport;
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

    public static Passport of(Passport passport) {
        Passport newPassport = new Passport();
        newPassport.birthYear = new BirthYear(passport.birthYear.getValue());
        newPassport.issueYear = new IssueYear(passport.issueYear.getValue());
        newPassport.expirationYear = new ExpirationYear(passport.expirationYear.getValue());
        newPassport.height = new Height(passport.height.getValue());
        newPassport.hairColor = new HairColor(passport.hairColor.getValue());
        newPassport.eyeColor = new EyeColor(passport.eyeColor.getValue());
        newPassport.passportId = new PassportId(passport.passportId.getValue());
        newPassport.countryId = new CountryId(passport.countryId.getValue());

        return newPassport;
    }

    public String toString() {
        String stringPassport = "";
        stringPassport += birthYear.getPassportString();
        stringPassport += " " + issueYear.getPassportString();
        stringPassport += " " + expirationYear.getPassportString();
        stringPassport += " " + height.getPassportString();
        stringPassport += " " + hairColor.getPassportString();
        stringPassport += " " + eyeColor.getPassportString();
        stringPassport += " " + passportId.getPassportString();
        stringPassport += " " + countryId.getPassportString();
        return stringPassport.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null) return false;
        if (!(o instanceof Passport)) return false;
        Passport otherPassport = (Passport) o;
        return Objects.equals(birthYear, otherPassport.birthYear)
                && Objects.equals(issueYear, otherPassport.issueYear)
                && Objects.equals(expirationYear, otherPassport.expirationYear)
                && Objects.equals(height, otherPassport.height)
                && Objects.equals(hairColor, otherPassport.hairColor)
                && Objects.equals(eyeColor, otherPassport.eyeColor)
                && Objects.equals(passportId, otherPassport.passportId)
                && Objects.equals(countryId, otherPassport.countryId);
    }

    public void setIssueYear(IssueYear issueYear) {
        this.issueYear = issueYear;
    }

    public void setExpirationYear(ExpirationYear expirationYear) {
        this.expirationYear = expirationYear;
    }

    public void setHeight(Height height) {
        this.height = height;
    }

    public void setHairColor(HairColor hairColor) {
        this.hairColor = hairColor;
    }

    public void setEyeColor(EyeColor eyeColor) {
        this.eyeColor = eyeColor;
    }

    public void setPassportId(PassportId passportId) {
        this.passportId = passportId;
    }

    public void setCountryId(CountryId countryId) {
        this.countryId = countryId;
    }

    public void setBirthYear(BirthYear birthYear) {
        this.birthYear = birthYear;
    }
}
