package org.util.AoC2020.D07;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BagWithContent extends HashMap<String, Long> {
    private static final Pattern bagContent = Pattern.compile("(\\d) ([\\w ]+) bag(s)?");

    public BagWithContent(String contentString) {
        super();
        Matcher matcher = bagContent.matcher(contentString);
        String bagType;
        long number;
        if (matcher.matches()) {
            bagType = matcher.group(2);
            number = Long.parseLong(matcher.group(1));
        } else {
            bagType = "No Bag";
            number = 0;
        }
        this.put(bagType, number);
    }


    public boolean canContain(String entry) {
        return containsKey(entry);
    }

    public void addContent(BagWithContent bagWithContent) {
        bagWithContent.forEach(this::putIfAbsent);
    }
}
