package D06;

import net.jqwik.api.Example;
import org.util.AoC2020.D06.AnswersAnalyzer;
import org.util.AoC2020.Helpers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswersAnalyzerTest {
    @Example
    void countUniquesSum_ofExampleFile_is11() {
        long expected = 11;
        final List<String> groupAnswers = Helpers.parseParagraphsAsStrings("d06-tests.txt");
        AnswersAnalyzer answersAnalyzer = new AnswersAnalyzer(groupAnswers);
        long actual = answersAnalyzer.countUniquesSum();
        assertThat(expected).isEqualTo(actual);
    }

    @Example
    void countCommonSum_ofExampleFile_is6() {
        long expected = 6;
        final List<String> groupAnswers = Helpers.parseParagraphsAsStrings("d06-tests.txt");
        AnswersAnalyzer answersAnalyzer = new AnswersAnalyzer(groupAnswers);
        long actual = answersAnalyzer.countCommonSum();
        assertThat(expected).isEqualTo(actual);
    }
}
