package D04;

import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.LowerChars;
import net.jqwik.api.constraints.StringLength;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.util.AoC2020.D04.*;
import org.util.AoC2020.Helpers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PassportVerificationTest {
    @Property
    void EqualityWithItself(@ForAll("fullPassport") Passport passport) {
        Passport other = Passport.of(passport);
        assertThat(other).isEqualTo(passport);
    }

    @Property
    void PassportOnlyYearsIsomorphismWithString(
            @ForAll("onlyYears") Passport passport
    ) {
        assertIsomorphicConversion(passport);
    }

    @Property
    void PassportOnlyHeightIsomorphismWithString(
            @ForAll("onlyHeight") Passport passport
    ) {
        assertIsomorphicConversion(passport);
    }

    @Property
    void PassportOnlyColorsIsomorphicConversion(
            @ForAll("onlyColors") Passport passport
    ) {
        assertIsomorphicConversion(passport);
    }

    @Property
    void PassportOnlyIdsIsomorphicConversion(
            @ForAll("onlyIds") Passport passport
    ) {
        assertIsomorphicConversion(passport);
    }

    @Property
    void PassportIsomorphicConversion(
            @ForAll("fullPassport") Passport passport
    ) {
        assertIsomorphicConversion(passport);
    }

    @Property
    void PassportWithoutCountryIdIsValid(
            @ForAll("noCountry") Passport passport
    ) {
        Assertions.assertTrue(PassportValidation.isValid(passport));
    }

    @Test
    void LoadedTestsHave2ValidPassports(){
        List<String> passportList = Helpers.parseParagraphsAsStrings("d04-tests.txt");
        final long expected = 2;
        final long actual = PassportValidation.countValidPassports(passportList);
        Assertions.assertEquals(expected, actual);
    }

    @Property
    void HeightUnits(
            @ForAll @IntRange(min = 80, max = 250) int value,
            @ForAll @StringLength(2) @LowerChars String unit
    ) {
        Height height = new Height("" + value + unit);
        assertThat(value).isEqualTo(height.getValue());
    }

    private void assertIsomorphicConversion(@ForAll("onlyYears") Passport passport) {
        String stringifyPassword = passport.toString();
        Passport convertedBack = Passport.of(stringifyPassword);
        assertThat(convertedBack).isEqualTo(passport);
    }

    @Provide
    Arbitrary<String> height() {
        Arbitrary<Integer> values = Arbitraries.integers().between(80, 250);
        Arbitrary<String> units = Arbitraries.of("in", "cm");
        return Combinators.combine(values, units).as((value, unit) -> value + unit);
    }

    @Provide
    Arbitrary<Passport> onlyColors() {
        Arbitrary<HairColor> hairColors = getHairColorArbitrary();
        Arbitrary<EyeColor> eyeColors = getEyeColorArbitrary();
        return Combinators
                .combine(hairColors, eyeColors)
                .as(
                        (hairColor, eyeColor) -> {
                            Passport passport = new Passport();
                            passport.setHairColor(hairColor);
                            passport.setEyeColor(eyeColor);
                            return passport;
                        });

    }

    @Provide
    Arbitrary<Passport> onlyHeight() {
        Arbitrary<Height> heights = getHeightArbitrary();
        return heights.map(height -> {
            Passport passport = new Passport();
            passport.setHeight(height);
            return passport;
        });
    }

    @Provide
    Arbitrary<Passport> onlyYears() {
        Arbitrary<Integer> birthYears = getYearIntArbitrary();
        Arbitrary<Integer> issueYears = getYearIntArbitrary();
        Arbitrary<Integer> expirationYears = getYearIntArbitrary();

        return Combinators
                .combine(birthYears, issueYears, expirationYears)
                .as((birthYear, issueYear, expirationYear) -> {
                    Passport passport = new Passport();
                    passport.setBirthYear(new BirthYear(birthYear));
                    passport.setIssueYear(new IssueYear(issueYear));
                    passport.setExpirationYear(new ExpirationYear(expirationYear));
                    return passport;
                });
    }

    @Provide
    Arbitrary<Passport> noCountry() {
        Arbitrary<Integer> birthYears = getYearIntArbitrary();
        Arbitrary<Integer> issueYears = getYearIntArbitrary();
        Arbitrary<Integer> expirationYears = getYearIntArbitrary();
        Arbitrary<Height> heights = getHeightArbitrary();
        Arbitrary<HairColor> hairColors = getHairColorArbitrary();
        Arbitrary<EyeColor> eyeColors = getEyeColorArbitrary();
        Arbitrary<PassportId> passportIds = getPassportIdArbitrary();
        return Combinators
                .combine(birthYears, issueYears, expirationYears, heights, hairColors, eyeColors, passportIds)
                .as(
                        (birthYear, issueYear, expirationYear, height, hairColor, eyeColor, passportId) -> {
                            Passport passport = new Passport();
                            passport.setBirthYear(new BirthYear(birthYear));
                            passport.setIssueYear(new IssueYear(issueYear));
                            passport.setExpirationYear(new ExpirationYear(expirationYear));
                            passport.setHeight(height);
                            passport.setHairColor(hairColor);
                            passport.setEyeColor(eyeColor);
                            passport.setPassportId(passportId);
                            return passport;
                        });
    }

    @Provide
    Arbitrary<Passport> fullPassport() {
        Arbitrary<Integer> birthYears = getYearIntArbitrary();
        Arbitrary<Integer> issueYears = getYearIntArbitrary();
        Arbitrary<Integer> expirationYears = getYearIntArbitrary();
        Arbitrary<Height> heights = getHeightArbitrary();
        Arbitrary<HairColor> hairColors = getHairColorArbitrary();
        Arbitrary<EyeColor> eyeColors = getEyeColorArbitrary();
        Arbitrary<PassportId> passportIds = getPassportIdArbitrary();
        Arbitrary<CountryId> countryIds = getCountryIdArbitrary();
        return Combinators
                .combine(birthYears, issueYears, expirationYears, heights, hairColors, eyeColors, passportIds, countryIds)
                .as(
                        (birthYear, issueYear, expirationYear, height, hairColor, eyeColor, passportId, countryId) -> {
                            Passport passport = new Passport();
                            passport.setBirthYear(new BirthYear(birthYear));
                            passport.setIssueYear(new IssueYear(issueYear));
                            passport.setExpirationYear(new ExpirationYear(expirationYear));
                            passport.setHeight(height);
                            passport.setHairColor(hairColor);
                            passport.setEyeColor(eyeColor);
                            passport.setPassportId(passportId);
                            passport.setCountryId(countryId);
                            return passport;
                        });
    }

    @Provide
    Arbitrary<Passport> onlyIds() {
        Arbitrary<PassportId> passportIds = getPassportIdArbitrary();
        Arbitrary<CountryId> countryIds = getCountryIdArbitrary();
        return Combinators
                .combine(passportIds, countryIds)
                .as(
                        (passportId, countryId) -> {
                            Passport passport = new Passport();
                            passport.setPassportId(passportId);
                            passport.setCountryId(countryId);
                            return passport;
                        });
    }

    private Arbitrary<CountryId> getCountryIdArbitrary() {
        return Arbitraries.integers().between(1, 999).map(CountryId::new);
    }

    private Arbitrary<PassportId> getPassportIdArbitrary() {
        return Arbitraries
                .strings()
                .numeric()
                .ofLength(9)
                .map(PassportId::new);
    }

    private Arbitrary<Height> getHeightArbitrary() {
        return Arbitraries
                .integers()
                .between(80, 250)
                .map(Height::new);
    }

    private Arbitrary<Integer> getYearIntArbitrary() {
        return Arbitraries
                .integers()
                .between(1900, 2020);
    }

    private Arbitrary<EyeColor> getEyeColorArbitrary() {
        return Arbitraries
                .strings()
                .withChars('1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F')
                .ofLength(6)
                .map(EyeColor::new);
    }


    private Arbitrary<HairColor> getHairColorArbitrary() {
        return Arbitraries
                .strings()
                .withChars('1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F')
                .ofLength(6)
                .map(HairColor::new);
    }

}
