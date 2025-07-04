package com.incubyte;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringCalculatorTest {
    private final StringCalculator calculator = new StringCalculator();

    @Test
    void shouldReturnZeroOnEmptyString() {
        assertEquals(0, calculator.add(""));
    }

    @Test
    void shouldReturnNumberWhenSingleNumberProvided() {
        assertEquals(1, calculator.add("1"));
    }

    @Test
    void shouldReturnSumWhenTwoNumbersProvided() {
        assertEquals(3, calculator.add("1,2"));
    }

    @Test
    void shouldReturnSumForMultipleCommaSeparatedNumbers() {
        assertEquals(15, calculator.add("1,2,3,4,5"));
    }

    @Test
    void shouldReturnSumWhenNewLineIsUsedAsDelimiter() {
        assertEquals(6, calculator.add("1\n2,3"));
    }

}