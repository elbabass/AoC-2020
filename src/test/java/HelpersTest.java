import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.util.AoC2020.Helpers;

public class HelpersTest {
    int[] baseList = {1721,
            979,
            366,
            299,
            675,
            1456};

    @Test
    void ListOfIntCanBeConvertedBack() {
        String baseString = """
                1721
                979
                366
                299
                675
                1456""";
        int[] actual = Helpers.listOfIntFromText(baseString);
        Assert.assertArrayEquals(baseList, actual);
    }
}