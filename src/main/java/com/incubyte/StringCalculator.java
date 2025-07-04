package com.incubyte;

import java.util.Arrays;

public class StringCalculator {
    public int add(String numbers) {
        if (numbers.isEmpty()) return 0;
        String[] parts = numbers.split("[,\n]");
        return Arrays.stream(parts)
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .sum();
    }
}