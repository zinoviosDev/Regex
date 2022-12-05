package fr.marcdev;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static final List<String> licences;
    private static final Pattern DIGIT_REGEX;

    static {
        licences = Arrays.asList(
                "Lic DR 101 - a licence",
                "Lic ER 001 - a licence",
                "Lic CR 002 - a licence",
                "Lic FR 102 - a licence",
                "Lic AR 005 - a licence",
                "Lic BR 103 - a licence"
        );
        DIGIT_REGEX = Pattern.compile("\\d{3}");
    }

    public static Collection<String> getLicencesSortedByNumber(List<String> licences) {
        return licences.stream().collect(() -> Collections.synchronizedSortedMap(new TreeMap<Integer, String>()),
                (s, e) -> s.put(getLicenceNumber(e), e), Map::putAll).values();
    }

    private static int getLicenceNumber(String licence) {
        int result = -1;
        Matcher matcher = DIGIT_REGEX.matcher(licence);
        if (matcher.find()) {
            result = Integer.parseInt(matcher.group());
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(getLicencesSortedByNumber(licences));
    }
}
