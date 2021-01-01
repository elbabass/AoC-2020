package org.util.AoC2020.D06;

import java.util.List;
import java.util.stream.Collectors;

public class AnswersAnalyzer {
    private final List<GroupAnswers> groupAnswers;

    public AnswersAnalyzer(List<String> groupAnswers) {
        this.groupAnswers = groupAnswers.stream().map(GroupAnswers::new).collect(Collectors.toList());
    }

    public long countUniquesSum() {
        return groupAnswers.stream().mapToLong(GroupAnswers::countDistinct).sum();
    }

    public long countCommonSum() {
        return groupAnswers.stream().mapToLong(GroupAnswers::countRespondedByAll).sum();
    }
}
