package org.util.AoC2020.D06;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GroupAnswers {
    private final List<List<Character>> groupAnswers;

    public GroupAnswers(String answersString) {
        groupAnswers = new ArrayList<>();
        List<Character> answers = new ArrayList<>();
        for (char c : answersString.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                answers.add(c);
            } else {
                answers = addAnswersToGroup(answers);
            }
        }
        addAnswersToGroup(answers);
    }

    private List<Character> addAnswersToGroup(List<Character> answers) {
        if (!answers.isEmpty()) {
            groupAnswers.add(answers);
            answers = new ArrayList<>();
        }
        return answers;
    }

    public long countDistinct() {
        return groupAnswers
                .stream()
                .flatMap(List::stream)
                .distinct()
                .count();
    }

    public long countRespondedByAll() {
        return groupAnswers
                .stream()
                .map(
                        answers -> answers
                                .stream()
                                .filter(a -> groupAnswers
                                        .stream()
                                        .allMatch(others -> others.contains(a))
                                )
                        .collect(Collectors.toList())

                )
                .flatMap(List::stream)
                .distinct()
                .count();
    }
}
