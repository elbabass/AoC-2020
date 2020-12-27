package org.util.AoC2020.D04;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Height extends PassportField<Number> {
    public Height() {
        super();
    }

    public Height(Number assignment) {
        super(assignment);
    }

    public static PassportField<Number> of(String heightString) {
        final Matcher matcher = Pattern
                .compile(("^(\\d+)"))
                .matcher(heightString);
        if (matcher.find()) {
            return new Height(Integer.parseInt(matcher.group(1)));
        }
        else {
            return new Height(Integer.parseInt(heightString));
        }
    }

    @Override
    String getKeyName() {
        return HEIGHT_KEY;
    }
}
