package org.util.AoC2020.D04.Passport;

import org.util.AoC2020.D04.Passport.Fields.*;

import static org.util.AoC2020.D04.Passport.Fields.PassportField.*;

public class MutablePassport {
    public BirthYear birthYear = new BirthYear();
    public IssueYear issueYear = new IssueYear();
    public ExpirationYear expirationYear = new ExpirationYear();
    public Height height = new Height();
    public HairColor hairColor = new HairColor();
    public EyeColor eyeColor = new EyeColor();
    public PassportId passportId = new PassportId();
    public CountryId countryId = new CountryId();

    void assignFieldsFromKeyValueStrings(String[] keyValue) {
        String key = keyValue[0];
        String value = keyValue[1];
        switch (key) {
            case BIRTH_YEAR_KEY -> this.birthYear = (BirthYear) BirthYear.of(value);
            case ISSUE_YEAR_KEY -> this.issueYear = (IssueYear) IssueYear.of(value);
            case EXPIRATION_YEAR_KEY -> this.expirationYear = (ExpirationYear) ExpirationYear.of(value);
            case HEIGHT_KEY -> this.height = (Height) Height.of(value);
            case HAIR_COLOR_KEY -> this.hairColor = (HairColor) HairColor.of(value);
            case EYE_COLOR_KEY -> this.eyeColor = (EyeColor) EyeColor.of(value);
            case PASSWORD_ID_KEY -> this.passportId = (PassportId) PassportId.of(value);
            case COUNTRY_ID_KEY -> this.countryId = (CountryId) CountryId.of(value);
            default -> System.out.println("Field not found : " + key);
        }
    }

    void fromFields(PassportField<?>[] fields) {
        for (PassportField<?> passportField : fields) {
            switch (passportField.getKeyName()) {
                case BIRTH_YEAR_KEY -> this.birthYear = new BirthYear((Integer) passportField.getValue());
                case ISSUE_YEAR_KEY -> this.issueYear = new IssueYear((Integer) passportField.getValue());
                case EXPIRATION_YEAR_KEY -> this.expirationYear = new ExpirationYear((Integer) passportField.getValue());
                case HEIGHT_KEY -> this.height = new Height(((Height)passportField).getValue());
                case HAIR_COLOR_KEY -> this.hairColor = new HairColor((String) passportField.getValue());
                case EYE_COLOR_KEY -> this.eyeColor = new EyeColor((String) passportField.getValue());
                case PASSWORD_ID_KEY -> this.passportId = new PassportId((String) passportField.getValue());
                case COUNTRY_ID_KEY -> this.countryId = new CountryId((Integer) passportField.getValue());
                default -> System.out.println("Field not found : " + passportField.getKeyName());
            }
        }
    }
}
