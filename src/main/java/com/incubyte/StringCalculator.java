package com.incubyte;

import java.util.Arrays;
import java.util.regex.Pattern;

public class StringCalculator {
    public int add(String numbers) {
        if (numbers.isEmpty()) return 0;

        String delimiter = "[,\n]";

        if (hasCustomDelimiter(numbers)) {
            delimiter = extractDelimiter(numbers);
            numbers = stripDelimiterHeader(numbers);
        }

        return sumOf(numbers.split(delimiter));
    }

    private boolean hasCustomDelimiter(String input) {
        return input.startsWith("//");
    }

    private String extractDelimiter(String input) {
        int delimiterEndIndex = input.indexOf("\n");
        String customDelimiter = input.substring(2, delimiterEndIndex);
        return Pattern.quote(customDelimiter);
    }

    private String stripDelimiterHeader(String input) {
        int delimiterEndIndex = input.indexOf("\n");
        return input.substring(delimiterEndIndex + 1);
    }

    private int sumOf(String[] numbers) {
        int sum = 0;
        for (String str : numbers) {
            int num = Integer.parseInt(str.trim());
            checkNegative(num);
            sum += num;
        }

        return sum;
    }

    private void checkNegative(int num) {
        if (num < 0) {
            throw new IllegalArgumentException("negatives not allowed: " + num);
        }
    }
}