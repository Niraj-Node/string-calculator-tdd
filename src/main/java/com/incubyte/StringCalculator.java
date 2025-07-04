package com.incubyte;

import java.util.Arrays;
import java.util.regex.Pattern;

public class StringCalculator {
    public int add(String numbers) {
        if (numbers.isEmpty()) return 0;

        String delimiter = "[,\n]";

        if (numbers.startsWith("//")) {
            int delimiterEndIndex = numbers.indexOf("\n");
            String customDelimiter = numbers.substring(2, delimiterEndIndex);
            delimiter = Pattern.quote(customDelimiter);
            numbers = numbers.substring(delimiterEndIndex + 1);
        }

        String[] parts = numbers.split(delimiter);
        return Arrays.stream(parts)
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .sum();
    }
}