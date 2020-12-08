import org.javatuples.Pair;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.util.AoC2020.Sumtiply;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class SumtiplyTest {
    int[] baseList = {1721,
            979,
            366,
            299,
            675,
            1456};
    @Test
    void SumtiplyOf2020() {
        assumeTrue(Sumtiply.IsSum2020(2020,0));
    }

    @Test
    void BaseListCorrrectesults() {
        Pair<Integer, Integer> result = Sumtiply.GetSumOf2020(baseList);
        Assert.assertEquals(Pair.with(1721, 299), result);
    }

    @Test
    void SumtiplicationIs514579() {
        int result = Sumtiply.Sumtiplication(baseList);
        Assert.assertEquals(514579, result);
    }
}
