package org.util.AoC2020.D04;

import org.util.AoC2020.Helpers;

import java.util.List;

public class Day4Main {
    public static void main(String[] args) {
        List<String> passportList = Helpers.parseParagraphsAsStrings("d04-inputs.txt");
        System.out.println("There are " + PassportValidation.countValidPassports(passportList) + " valid passport in this list");
    }
}
