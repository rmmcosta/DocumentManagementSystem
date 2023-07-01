package org.rmmcosta.utils;

public class Parser {
    public static String parseValueByLabel(String text, String label, String endPattern) {
        return parseValueByIndex(text, findIndex(text, label, 0) + label.length(), endPattern);
    }

    public static String parseValueByLabel(String text, String label, int skipTimes, String endPattern) {
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

    private static String parseValueByIndex(String text, int startIndex, String endPattern) {
        int endPatternIndex = endPattern.isEmpty() ? text.length() : text.indexOf(endPattern, startIndex);
        return text.substring(startIndex, endPatternIndex)
                .trim();
    }
}
