package com.incubyte;

import com.incubyte.listeners.AddListener;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringCalculator {
    private int callCount = 0;

    public int getCalledCount() {
        return callCount;
    }

    public void setAddListener(AddListener listener) {
    }

    public int add(String numbers) {
        callCount++;
        if (numbers.isEmpty()) return 0;

        String delimiter = "[,\n]";

        if (hasCustomDelimiter(numbers)) {
            delimiter = extractDelimiter(numbers);
            numbers = stripDelimiterHeader(numbers);
        }

        String[] parts = numbers.split(delimiter);
        return sumAndValidate(parts);
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

    private int sumAndValidate(String[] numbers) {
        List<Integer> negatives = new ArrayList<>();
        int sum = 0;

        for (String s : numbers) {
            int num = Integer.parseInt(s.trim());
            if (num < 0) {
                negatives.add(num);
            }
            sum += num;
        }

        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("negatives not allowed: " +
                    negatives.stream().map(Object::toString).collect(Collectors.joining(", ")));
        }

        return sum;
    }
}