package org.util.AoC2020;

public class AdventOfCodeMain {
    protected static final CurrentClassGetter currentClassGetter = new CurrentClassGetter();

    public static String getDailyInputFile(int dayNumber) {
        return String.format("%s%02d%s", "d", dayNumber, "-inputs.txt");
    }

    public static class CurrentClassGetter extends SecurityManager {
        public String getClassName() {
            return getClassContext()[1].getName();
        }

        public String getSimpleClassName() {
            return getClassContext()[1].getSimpleName();
        }
    }
}
