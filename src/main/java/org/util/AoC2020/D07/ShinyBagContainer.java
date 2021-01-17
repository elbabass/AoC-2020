package org.util.AoC2020.D07;

import java.util.List;

public class ShinyBagContainer {
    public final BagContentRules containers;

    public ShinyBagContainer(List<String> entries) {
        containers = new BagContentRules(entries);
    }

}
