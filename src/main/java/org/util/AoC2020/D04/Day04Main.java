package org.util.AoC2020.D04;

import org.util.AoC2020.AdventOfCodeMain;
import org.util.AoC2020.Helpers;

import java.util.List;

public class Day04Main extends AdventOfCodeMain {
    public static void main(String[] args) {
        List<String> passportList = Helpers.parseParagraphsAsStrings(getDailyInputFile(4));
        System.out.println("There are " + PassportValidation.countValidPassports(passportList) + " valid passport in this list");
    }
}
