package org.util.AoC2020.D04;

import java.time.Year;
import java.util.Arrays;
import java.util.Objects;

public class Passport implements Cloneable {
    public static final String BIRTH_YEAR_KEY = "byr";
    public static final String ISSUE_YEAR_KEY = "iyr";
    public static final String EXPIRATION_YEAR_KEY = "eyr";
    public static final String HEIGHT_KEY = "hgt";
    public static final String HAIR_COLOR_KEY = "hcl";
    public static final String EYE_COLOR_KEY = "ecl";
    public static final String PASSWORD_ID_KEY = "pid";
    public static final String COUNTRY_ID_KEY = "cid";
    public static final String[] fields = new String[]{
            BIRTH_YEAR_KEY,
            ISSUE_YEAR_KEY,
            EXPIRATION_YEAR_KEY,
            HEIGHT_KEY,
            HAIR_COLOR_KEY,
            EYE_COLOR_KEY,
            PASSWORD_ID_KEY,
            COUNTRY_ID_KEY
    };

    private Year birthYear;
    private Year issueYear;
    private Year expirationYear;
    private Height height;
    private HexadecimalColor hairColor;
    private HexadecimalColor eyeColor;
    private PassportId passportId;
    private CountryId countryId;

    public Passport() {

    }

    public Passport(String stringifyPassword) {
        Arrays
                .stream(stringifyPassword.split(" "))
                .map((stringField) -> stringField.split(":"))
                .forEach(((keyValue) -> {
                    String key = keyValue[0];
                    String value = keyValue[1];
                    switch (key) {
                        case BIRTH_YEAR_KEY -> birthYear = Year.of(Integer.parseInt(value));
                        case ISSUE_YEAR_KEY -> issueYear = Year.of(Integer.parseInt(value));
                        case EXPIRATION_YEAR_KEY -> expirationYear = Year.of(Integer.parseInt(value));
                        case HEIGHT_KEY -> height = new Height(value);
                        case HAIR_COLOR_KEY -> hairColor = new HexadecimalColor(value);
                        case EYE_COLOR_KEY -> eyeColor = new HexadecimalColor(value);
                        case PASSWORD_ID_KEY -> passportId = new PassportId(value);
                        case COUNTRY_ID_KEY -> countryId = new CountryId(Integer.parseInt(value));
                        default -> System.out.println("Field not found : " + key);
                    }
                }));
    }

    public boolean hasKey(String keyCode) {
        return switch (keyCode) {
            case BIRTH_YEAR_KEY -> birthYear != null;
            case ISSUE_YEAR_KEY -> issueYear != null;
            case EXPIRATION_YEAR_KEY -> expirationYear != null;
            case HEIGHT_KEY -> height != null;
            case HAIR_COLOR_KEY -> hairColor != null;
            case EYE_COLOR_KEY -> eyeColor != null;
            case PASSWORD_ID_KEY -> passportId != null;
            case COUNTRY_ID_KEY -> countryId != null;
            default -> false;
        };
    }

    public static Passport of(Passport passport) {
        Passport newPassport = new Passport();
        newPassport.birthYear = (passport.birthYear == null) ? null : Year.of(passport.birthYear.getValue());
        newPassport.issueYear = (passport.issueYear == null) ? null : Year.of(passport.issueYear.getValue());
        newPassport.expirationYear = (passport.expirationYear == null) ? null : Year.of(passport.expirationYear.getValue());
        newPassport.height = (passport.height == null) ? null : new Height(passport.height.getValue());
        newPassport.hairColor = (passport.hairColor == null) ? null : new HexadecimalColor(passport.hairColor.getValue());
        newPassport.eyeColor = (passport.eyeColor == null) ? null : new HexadecimalColor(passport.eyeColor.getValue());
        newPassport.passportId = (passport.passportId == null) ? null : new PassportId(passport.passportId.getValue());
        newPassport.countryId = (passport.countryId == null) ? null : new CountryId(passport.countryId.getValue());

        return newPassport;
    }

    public String toString() {
        String stringPassport = "";
        if (birthYear != null)
            stringPassport += BIRTH_YEAR_KEY + ":" + birthYear;
        if (issueYear != null)
            stringPassport += " " + ISSUE_YEAR_KEY + ":" + issueYear;
        if (expirationYear != null)
            stringPassport += " " + EXPIRATION_YEAR_KEY + ":" + expirationYear;
        if (height != null)
            stringPassport += " " + HEIGHT_KEY + ":" + height.getValue();
        if (hairColor != null)
            stringPassport += " " + HAIR_COLOR_KEY + ":" + hairColor.getValue();
        if (eyeColor != null)
            stringPassport += " " + EYE_COLOR_KEY + ":" + eyeColor.getValue();
        if (passportId != null)
            stringPassport += " " + PASSWORD_ID_KEY + ":" + passportId.getValue();
        if (countryId != null)
            stringPassport += " " + COUNTRY_ID_KEY + ":" + countryId.getValue();
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

    public void setIssueYear(Year issueYear) {
        this.issueYear = issueYear;
    }

    public void setExpirationYear(Year expirationYear) {
        this.expirationYear = expirationYear;
    }

    public void setHeight(Height height) {
        this.height = height;
    }

    public void setHairColor(HexadecimalColor hairColor) {
        this.hairColor = hairColor;
    }

    public void setEyeColor(HexadecimalColor eyeColor) {
        this.eyeColor = eyeColor;
    }

    public void setPassportId(PassportId passportId) {
        this.passportId = passportId;
    }

    public void setCountryId(CountryId countryId) {
        this.countryId = countryId;
    }

    public void setBirthYear(Year birthYear) {
        this.birthYear = birthYear;
    }
}
