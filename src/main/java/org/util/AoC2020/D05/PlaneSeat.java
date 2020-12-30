package org.util.AoC2020.D05;

public class PlaneSeat {
    private final int row;
    private final int column;

    public PlaneSeat(String binarySpacePartitioning) {
        row = BinaryEncoder
                .encode(binarySpacePartitioning.substring(0, 7), 'B');
        column = BinaryEncoder
                .encode(binarySpacePartitioning.substring(7, 10), 'R');
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getId() {
        return row * 8 + column;
    }
}
