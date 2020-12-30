package D05;

import net.jqwik.api.Example;
import org.junit.jupiter.api.Assertions;
import org.util.AoC2020.D05.IdProcessor;

import java.util.OptionalInt;
import java.util.stream.IntStream;

public class IdProcessorTest {

    @Example
    void min_OfTenInteger_HasMinAndMax() {
        int[] ints = IntStream.rangeClosed(3, 10).toArray();
        OptionalInt min = IdProcessor.min(ints);
        Assertions.assertEquals(OptionalInt.of(3), min);
    }

    @Example
    void max_OfTenInteger_HasMinAndMax() {
        int[] ints = IntStream.rangeClosed(3, 10).toArray();
        OptionalInt max = IdProcessor.max(ints);
        Assertions.assertEquals(OptionalInt.of(10), max);
    }

    @Example
    void hole_OfTenInteger_HasMinAndMax() {
        int[] ints = IntStream.rangeClosed(3, 10).filter(i -> i != 7).toArray();
        OptionalInt hole = IdProcessor.hole(ints);
        Assertions.assertEquals(OptionalInt.of(7), hole);
    }

}
