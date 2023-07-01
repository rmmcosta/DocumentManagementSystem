package org.rmmcosta.utils;

import java.util.Optional;

public class Parser {
    public static String parseValueByLabel(String text, String label, Optional<String> endPattern) {
        return parseValueByIndex(text, findIndex(text, label, 0) + label.length(), endPattern);
    }

    public static String parseValueByLabel(String text, String label, int skipTimes, Optional<String> endPattern) {
        return parseValueByIndex(text, findIndex(text, label, skipTimes) + label.length(), endPattern);
    }

    protected static int findIndex(String text, String label, int skipTimes) {
        int afterLabelIndex = 0;
        for (int i = 0; i <= skipTimes; i++) {
            afterLabelIndex++;
            afterLabelIndex = text.indexOf(label, afterLabelIndex);
        }
        return text.indexOf(label, afterLabelIndex);
    }

    private static String parseValueByIndex(String text, int startIndex, Optional<String> endPattern) {
        int endPatternIndex = endPattern.map(s -> text.indexOf(s, startIndex)).orElseGet(text::length);
        return text.substring(startIndex, endPatternIndex)
                .trim();
    }
}
