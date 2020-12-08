import org.javatuples.Pair;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.util.AoC2020.Sumtiply;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class SumtiplyTest {
    @Test
    void SumtiplyOf2020() {
        int x = 2020;
        assumeTrue(Sumtiply.IsSum2020(x,0));
    }

    @Test
    void BaseListCorrrectesults() {
        int[] baseList = {1721,
                979,
                366,
                299,
                675,
                1456};
        Pair<Integer, Integer> result = Sumtiply.GetSumOf2020(baseList);
        Assert.assertEquals(Pair.with(1721, 299), result);
    }

    @Test
    void SumtiplicationIs514579() {
        int[] baseList = {1721,
                979,
                366,
                299,
                675,
                1456};
        int result = Sumtiply.Sumtiplication(baseList);
        Assert.assertEquals(514579, result);
    }

    @Test
    void ListOfIntCanBeConvertedBack() {
        String baseString = """
                1721
                979
                366
                299
                675
                1456""";
        int[] expected = new int[]{1721,
                979,
                366,
                299,
                675,
                1456};
        int[] actual = Sumtiply.listOfIntFromText(baseString);
        Assert.assertArrayEquals(expected, actual);
    }
}
