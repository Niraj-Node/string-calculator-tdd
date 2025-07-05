package com.incubyte;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    void shouldSupportCustomDelimiterSpecifiedAtStart() {
        assertEquals(3, calculator.add("//;\n1;2"));
    }

    @Test
    void shouldSupportCustomDelimiterWithRegExpSpecialCharacters() {
        assertEquals(6, calculator.add("//*\n1*2*3"));
    }

    @Test
    void shouldThrowExceptionWhenSingleNegativeIsPassed() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.add("1,-2,3")
        );
        assertEquals("negatives not allowed: -2", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWithAllNegativeNumbersListed() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.add("-1,2,-4,5,-9")
        );
        assertEquals("negatives not allowed: -1, -4, -9", exception.getMessage());
    }

    @Test
    void shouldTrackNumberOfTimesAddMethodIsCalled() {
        StringCalculator calculator = new StringCalculator();
        calculator.add("1,2");
        calculator.add("3,4");
        assertEquals(2, calculator.getCalledCount());
    }
}