package com.incubyte;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringCalculator {
    public int add(String numbers) {
        if (numbers.isEmpty()) return 0;

        String delimiter = "[,\n]";

        if (hasCustomDelimiter(numbers)) {
            delimiter = extractDelimiter(numbers);
            numbers = stripDelimiterHeader(numbers);
        }

        String[] parts = numbers.split(delimiter);
        checkNegatives(parts);
        return sumOf(parts);
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
        return Arrays.stream(numbers)
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .sum();
    }

    private void checkNegatives(String[] numbers) {
        List<Integer> negatives = Arrays.stream(numbers)
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .filter(n -> n < 0)
                .boxed()
                .toList();

        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("negatives not allowed: " +
                    negatives.stream().map(Object::toString).collect(Collectors.joining(", ")));
        }
    }
}