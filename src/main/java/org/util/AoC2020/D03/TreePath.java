package org.util.AoC2020.D03;

import java.util.List;

public class TreePath {
    private final List<char[]> mapPatterns;

    public TreePath(List<char[]> mapPatterns) {
        this.mapPatterns = mapPatterns;
    }

    public static char treeOrNot(char[] row, int right) {
        return row[right % row.length];
    }

    public char treeOrNot(int down, int right) {
        return treeOrNot(mapPatterns.get(down), right);
    }

    public int treesOnSlope(int down, int right) {
        return treesOnSlope(down, right, down, right);
    }

    private int treesOnSlope(int down, int right, int row, int column) {
        int count = countTree(row, column);
        if (row >= mapPatterns.size() - 1) {
            return count;
        }
        return count + treesOnSlope(down, right, row + down, column + right);
    }

    private int countTree(int row, int column) {
        return (treeOrNot(row, column) == '#') ? 1 : 0;
    }
}
