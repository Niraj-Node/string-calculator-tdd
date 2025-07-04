package com.incubyte;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringCalculatorTest {
    private final StringCalculator calculator = new StringCalculator();

    @Test
    void shouldReturnZeroOnEmptyString() {
        assertEquals(0, calculator.add(""));
    }
}