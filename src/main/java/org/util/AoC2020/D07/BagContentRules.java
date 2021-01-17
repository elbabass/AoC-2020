package org.util.AoC2020.D07;

import org.javatuples.KeyValue;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BagContentRules {
    HashMap<String, BagWithContent> bagContentRulesMap;

    private static final Pattern pattern = Pattern.compile("(\\w+ \\w+) bags contain ([\\d\\w, ]+)");

    public BagContentRules(List<String> entries) {
        HashMap<String, BagWithContent> bagRules = new HashMap<>();
        entries.stream()
                .map(BagContentRules::getCanContainBagPair)
                .filter(result ->
                        result != null
                                && bagRules.put(result.getKey(), result.getValue()) != null
                )
                .map(result ->
                        "Duplicate key : old = "
                                + bagRules.get(result.getKey())
                                + ", new = " + result.getValue())
                .forEach(System.out::println);
        bagRules.forEach((bagType, bagWithContent) ->
                bagRules.forEach((bagType2, bagWithContent2) -> {
                    if (bagWithContent.canContain(bagType2)) {
                        bagWithContent.addContent(bagWithContent2);
                    }
                }));
        bagContentRulesMap = bagRules;
    }

    public static KeyValue<String, BagWithContent> getCanContainBagPair(String entry) {
        Matcher matcher = pattern.matcher(entry);
        if (matcher.matches()) {
            BagWithContent canContainBag = new BagWithContent(matcher.group(2));
            return KeyValue.with(matcher.group(1), canContainBag);
        }
        return null;
    }

    public long countShinyBagsContainers() {
        return bagContentRulesMap
                .entrySet()
                .stream()
                .filter(bagContent -> bagContent.getValue().canContain("shiny")
                        || bagContent.getKey().contains("shiny"))
                .count();
    }
}
