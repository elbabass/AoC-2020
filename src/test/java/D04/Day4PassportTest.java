package D04;

import net.jqwik.api.*;
import net.jqwik.api.arbitraries.StringArbitrary;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.LowerChars;
import net.jqwik.api.constraints.StringLength;
import org.javatuples.Pair;
import org.junit.jupiter.api.Assertions;
import org.util.AoC2020.D04.*;
import org.util.AoC2020.D04.Passport.Fields.*;
import org.util.AoC2020.D04.Passport.Passport;
import org.util.AoC2020.Helpers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Day4PassportTest {
    @Property
    void EqualityWithItself(@ForAll("fullPassport") Passport passport) {
        Passport other = Passport.copyOf(passport);
        assertThat(other).isEqualTo(passport);
    }

    @Group
    public class FieldsStringBijection {
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
        void HeightUnits(
                @ForAll @IntRange(min = 80, max = 250) int value,
                @ForAll @StringLength(2) @LowerChars String unit
        ) {
            final String heightString = "" + value + unit;
            Height height = (Height) Height.of(heightString);
            assertThat(heightString).isEqualTo(height.toString());
        }
    }

    @Group
    public class PassportVerification {
        @Property
        void PassportWithoutCountryIdIsValid(
                @ForAll("noCountry") Passport passport
        ) {
            Assertions.assertTrue(PassportValidation.isValid(passport));
        }

        @Example
        void LoadedTestsHave2ValidPassports() {
            List<String> passportList = Helpers.parseParagraphsAsStrings("d04-tests.txt");
            final long expected = 2;
            final long actual = PassportValidation.countValidPassports(passportList);
            Assertions.assertEquals(expected, actual);
        }
    }

    @Group
    public class PassportImmutability {
        @Property
        void PassportCanBeCreatedWithFieldList(
                @ForAll("listOfAllFields") PassportField<?>[] fields
        ) {
            PassportField<?>[] createdFields = new Passport(fields).getFields();
            assertThat(fields).isEqualTo(createdFields);
        }
    }

    @Provide
    Arbitrary<Height> heightCm() {
        Arbitrary<Integer> values = Arbitraries.integers().between(80, 250);
        return values.map(integer -> "" + integer + "cm").map(s -> (Height) Height.of(s));
    }

    @Provide
    Arbitrary<Passport> onlyColors() {
        Arbitrary<HairColor> hairColors = getHairColorArbitrary();
        Arbitrary<EyeColor> eyeColors = getEyeColorArbitrary();
        return Combinators
                .combine(hairColors, eyeColors)
                .as(Passport::new);

    }

    @Provide
    Arbitrary<Passport> onlyHeight() {
        Arbitrary<Height> heights = getHeightArbitrary();
        return heights.map(Passport::new);
    }

    @Provide
    Arbitrary<Passport> onlyYears() {
        Arbitrary<BirthYear> birthYears = getBirthYearArbitrary();
        Arbitrary<IssueYear> issueYears = getIssueYearArbitrary();
        Arbitrary<ExpirationYear> expirationYears = getExpirationYearArbitrary();

        return Combinators
                .combine(birthYears, issueYears, expirationYears)
                .as(Passport::new);
    }

    @Provide
    Arbitrary<Passport> onlyIds() {
        Arbitrary<PassportId> passportIds = getPassportIdArbitrary();
        Arbitrary<CountryId> countryIds = getCountryIdArbitrary();
        return Combinators
                .combine(passportIds, countryIds)
                .as(Passport::new);
    }

    @Provide
    Arbitrary<Passport> noCountry() {
        return getPassportArbitrary(false);
    }

    @Provide
    Arbitrary<Passport> fullPassport() {
        return getPassportArbitrary(true);
    }

    @Provide
    public Arbitrary<PassportField<?>[]> listOfAllFields() {
        return getSetOfFieldsArbitrary(this::getFields);
    }

    private PassportField<?>[] getFields(BirthYear birthYear, IssueYear issueYear, ExpirationYear expirationYear, Height height, HairColor hairColor, EyeColor eyeColor, PassportId passportId, CountryId countryId) {
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

    private Arbitrary<PassportField<?>[]> getSetOfFieldsArbitrary(Combinators.F8<BirthYear, IssueYear, ExpirationYear, Height, HairColor, EyeColor, PassportId, CountryId, PassportField<?>[]> combinator) {
        Arbitrary<BirthYear> birthYears = getBirthYearArbitrary();
        Arbitrary<IssueYear> issueYears = getIssueYearArbitrary();
        Arbitrary<ExpirationYear> expirationYears = getExpirationYearArbitrary();
        Arbitrary<Height> heights = getHeightArbitrary();
        Arbitrary<HairColor> hairColors = getHairColorArbitrary();
        Arbitrary<EyeColor> eyeColors = getEyeColorArbitrary();
        Arbitrary<PassportId> passportIds = getPassportIdArbitrary();
        Arbitrary<CountryId> countryIds = getCountryIdArbitrary();

        return Combinators
                .combine(birthYears, issueYears, expirationYears, heights, hairColors, eyeColors, passportIds, countryIds)
                .as(combinator);
    }

    private void assertIsomorphicConversion(@ForAll("onlyYears") Passport passport) {
        Passport convertedBack = new Passport(passport.toString());
        assertThat(convertedBack).isEqualTo(passport);
    }

    private Arbitrary<Passport> getPassportArbitrary(boolean includeCountry) {
        Arbitrary<BirthYear> birthYears = getBirthYearArbitrary();
        Arbitrary<IssueYear> issueYears = getIssueYearArbitrary();
        Arbitrary<ExpirationYear> expirationYears = getExpirationYearArbitrary();
        Arbitrary<Height> heights = getHeightArbitrary();
        Arbitrary<HairColor> hairColors = getHairColorArbitrary();
        Arbitrary<EyeColor> eyeColors = getEyeColorArbitrary();
        Arbitrary<PassportId> passportIds = getPassportIdArbitrary();
        Arbitrary<CountryId> countryIds = getCountryIdArbitrary();

        if (includeCountry) {
            return Combinators
                    .combine(birthYears, issueYears, expirationYears, heights, hairColors, eyeColors, passportIds, countryIds)
                    .as(Passport::new);
        } else {
            return Combinators
                    .combine(birthYears, issueYears, expirationYears, heights, hairColors, eyeColors, passportIds)
                    .as(Passport::new);
        }
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
                .map(i -> new Height(Pair.with(i, "cm")));
    }

    private Arbitrary<BirthYear> getBirthYearArbitrary() {
        return getYearIntArbitrary(1920, 2002).map(BirthYear::new);
    }

    private Arbitrary<IssueYear> getIssueYearArbitrary() {
        return getYearIntArbitrary(2010, 2020).map(IssueYear::new);
    }

    private Arbitrary<ExpirationYear> getExpirationYearArbitrary() {
        return getYearIntArbitrary(2020, 2030).map(ExpirationYear::new);
    }

    private Arbitrary<Integer> getYearIntArbitrary(int min, int max) {
        return Arbitraries
                .integers()
                .between(min, max);
    }

    private Arbitrary<EyeColor> getEyeColorArbitrary() {
        return getHexColorStringArbitrary()
                .map(s -> new EyeColor("#" + s));
    }

    private Arbitrary<HairColor> getHairColorArbitrary() {
        return getHexColorStringArbitrary()
                .map(s -> new HairColor("#" + s));
    }

    private StringArbitrary getHexColorStringArbitrary() {
        return Arbitraries
                .strings()
                .withChars('1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F')
                .ofLength(6);
    }

}
