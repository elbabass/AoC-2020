package org.util.AoC2020.D04;

public class CountryId extends PassportField<Integer> {
    public CountryId() {
        super();
    }

    public CountryId(Integer identifier) {
        super(identifier);
    }

    public static PassportField<Integer> of(String countryIdentifier) {
        return new CountryId(Integer.parseInt(countryIdentifier));
    }

    @Override
    String getKeyName() {
        return COUNTRY_ID_KEY;
    }
}
