package org.util.AoC2020.D07;

public class CanContainBag {
    String bagType;
    long number;

    public CanContainBag(String bagType, long number) {
        this.bagType = bagType;
        this.number = number;
    }

    @Override
    public String toString() {
        return "CanContainBag{" +
                "bagType='" + bagType + '\'' +
                ", number=" + number +
                '}';
    }
}
