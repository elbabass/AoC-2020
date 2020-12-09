import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.util.AoC2020.Sumtiply;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class SumtiplyTest {
    int[] baseList = {1721,
            979,
            366,
            299,
            675,
            1456};
    @Test
    void BaseListCorrrectesults() {
        List<Integer> result = Sumtiply.GetSumOf2020(baseList);
        Assert.assertEquals(List.of(1721, 299), result);
    }

    @Test
    void SumtiplicationOf2Numbers514579() {
        Optional<Integer> result = Sumtiply.SumtiplicationOf(baseList, 2);
        Assert.assertEquals(Optional.of(514579), result);
    }

    @Test
    void TestSumtiplyOfOptional() {
        Optional<Integer> result = Sumtiply.Sumtiplication(baseList);
        Assert.assertEquals(Optional.of(514579), result);
    }

    @Test
    void TestSumtiplyOfNEmpty() {
        Optional<Integer> result = Sumtiply.Sumtiplication(new int[] {});
        Assert.assertEquals(Optional.empty(), result);
    }
}
