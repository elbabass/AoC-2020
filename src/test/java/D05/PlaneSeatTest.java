package D05;

import net.jqwik.api.Example;
import org.util.AoC2020.D05.PlaneSeat;

import static org.assertj.core.api.Assertions.assertThat;

public class PlaneSeatTest {
    @Example
    void fromBinarySpacePartitioning_InputBFFFBBFRRR_ShouldBeRow70Column7Id567() {
        PlaneSeat planeSeat = new PlaneSeat("BFFFBBFRRR");
        int row = planeSeat.getRow();
        int column = planeSeat.getColumn();
        int id = planeSeat.getId();
        int expectedRow = 70;
        int expectedColumn = 7;
        int expectedId = 567;
        assertThat(expectedRow).isEqualTo(row);
        assertThat(expectedColumn).isEqualTo(column);
        assertThat(expectedId).isEqualTo(id);
    }
}
