package org.util.AoC2020.D02;

import org.javatuples.Pair;
import org.util.AoC2020.AdventOfCodeMain;
import org.util.AoC2020.Helpers;

import java.util.List;

public class Day02Main extends AdventOfCodeMain {
    public static void main(String[] args) {
        List<Pair<PasswordPolicy, String>> passwordsToCheck = Helpers.getConvertedPolicyAndPasswordInputs(
                getDailyInputFile(2),
                Helpers::passwordPolicyAndStringFromEntry
        );
        System.out.println("Number of valid passwords with ranges in file : " + PasswordValidation.countFromList(passwordsToCheck, new PasswordRuleWithRange()));
        System.out.println("Number of valid passwords with positions in file : " + PasswordValidation.countFromList(passwordsToCheck, new PasswordRuleWithPositions()));
    }
}
