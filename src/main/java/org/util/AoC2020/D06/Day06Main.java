package org.util.AoC2020.D06;

import org.util.AoC2020.AdventOfCodeMain;
import org.util.AoC2020.Helpers;

import java.util.List;

public class Day06Main extends AdventOfCodeMain {
    public static void main(String[] args) {
        final List<String> groupAnswers = Helpers.parseParagraphsAsStrings(getDailyInputFile(6));
        AnswersAnalyzer answersAnalyzer = new AnswersAnalyzer(groupAnswers);
        long uniquesSum = answersAnalyzer.countUniquesSum();
        long commonSum = answersAnalyzer.countCommonSum();

        System.out.println("Sum of all groups distinct answers : " + uniquesSum);
        System.out.println("Sum of all groups common answers : " + commonSum);
    }
}
