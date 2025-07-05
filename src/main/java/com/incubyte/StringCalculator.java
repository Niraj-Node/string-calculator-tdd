package com.incubyte;

import com.incubyte.listeners.AddListener;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringCalculator {
    private int callCount = 0;
    private AddListener listener;
    private static final int MAX_ALLOWED_NUMBER = 1000;

    public int getCalledCount() {
        return callCount;
    }

    public void setAddListener(AddListener listener) {
        this.listener = listener;
    }

    public int add(String numbers) {
        callCount++;
        if (numbers.isEmpty()) {
            triggerAddEvent(numbers, 0);
            return 0;
        }

        String delimiter = "[,\n]";

        if (hasCustomDelimiter(numbers)) {
            delimiter = extractDelimiter(numbers);
            numbers = stripDelimiterHeader(numbers);
        }

        String[] parts = numbers.split(delimiter);
        int sum = sumAndValidate(parts);
        triggerAddEvent(numbers, sum);
        return sum;
    }

    private void triggerAddEvent(String input, int result) {
        if (listener != null) {
            listener.onAdd(input, result);
        }
    }

    private boolean hasCustomDelimiter(String input) {
        return input.startsWith("//");
    }

    private String extractDelimiter(String input) {
        int delimiterEndIndex = input.indexOf("\n");
        String customDelimiter = input.substring(2, delimiterEndIndex);

        // Match all delimiters enclosed in []
        if (customDelimiter.startsWith("[") && customDelimiter.endsWith("]")) {
            List<String> delimiters = new ArrayList<>();
            int i = 0;
            while ((i = customDelimiter.indexOf("[", i)) != -1) {
                int end = customDelimiter.indexOf("]", i);
                String d = customDelimiter.substring(i + 1, end);
                delimiters.add(Pattern.quote(d));
                i = end + 1;
            }
            return String.join("|", delimiters);
        }

        // Single character delimiter
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
            } else if (num <= MAX_ALLOWED_NUMBER) {
                sum += num;
            }
        }

        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("negatives not allowed: " +
                    negatives.stream().map(Object::toString).collect(Collectors.joining(", ")));
        }

        return sum;
    }
}