package org.util.AoC2020.D04.Passport.Fields;

import org.javatuples.Pair;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Height extends PassportField<Pair<Number, String>> {
    public Height() {
        super();
    }

    public Height(Pair<Number, String> assignment) {
        super(assignment);
    }

    public static PassportField<Pair<Number, String>> of(String heightString) {
        final Matcher matcher = Pattern
                .compile(("^(\\d+)([a-z]{2})"))
                .matcher(heightString);
        if (matcher.find()) {
            return new Height(Pair.with(Integer.parseInt(matcher.group(1)), matcher.group(2)));
        }
        else {
            return new Height(Pair.with(Integer.parseInt(heightString), "cm"));
        }
    }

    @Override
    public String getKeyName() {
        return HEIGHT_KEY;
    }

    @Override
    public String toString() {
        assert value != null;
        return value.getValue0() + value.getValue1();
    }
}
