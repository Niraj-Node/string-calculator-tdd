package com.incubyte;

import java.util.Arrays;

public class StringCalculator {
    public int add(String numbers) {
        if (numbers.isEmpty()) return 0;

        String delimiter = "[,\n]";

        if (numbers.startsWith("//")) {
            int delimiterEndIndex = numbers.indexOf("\n");
            delimiter = numbers.substring(2, delimiterEndIndex);
            numbers = numbers.substring(delimiterEndIndex + 1);
        }

        String[] parts = numbers.split(delimiter);
        return Arrays.stream(parts)
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .sum();
    }
}