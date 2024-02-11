package com.incubyte.assessment.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class StringCalculatorServiceTest {
    @Autowired
    StringCalculatorService stringCalculatorService;

    @Test
    void testEmptyStringReturnsZero() {
        assertEquals(0, stringCalculatorService.add(""));
    }

    @Test
    void testSingleNumberReturnsSameNumber() {
        assertEquals(2, stringCalculatorService.add("2"));
    }

    @Test
    void testCommaSeparatedNumbersReturnsSum() {
        assertEquals(3, stringCalculatorService.add("1,2"));
    }

    @Test
    void testNewlineSeparatedNumbersReturnsSum() {
        assertEquals(6, stringCalculatorService.add("1,2\n3"));
    }

    @Test
    void testNumbersWithSpaceAfterDelimitersReturnsSum() {
        assertEquals(6, stringCalculatorService.add("1, 2\n 3"));
    }

    @Test
    void testCustomDelimiterReturnsSum() {
        assertEquals(6, stringCalculatorService.add("//;\n1;2;3"));
    }

    @Test
    void testInvalidCustomDelimiterThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> stringCalculatorService.add("1;2//;\n;3"));
    }

    @Test
    void testNonDeclaredCustomDelimiterThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> stringCalculatorService.add("1;2;3"));
    }

    @Test
    void testNonNumericValuesThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> stringCalculatorService.add("1,2,Hi"));
    }

    @Test
    void testNegativeNumbersThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> stringCalculatorService.add("1,-8,2,-1"));
        assertEquals("Negative numbers not allowed -8, -1", exception.getMessage());
    }

    @Test
    void testNumbersInBatchesReturnsSumInBatches() {
        List<String> input = Arrays.asList(
                "1, 2",
                "1, 2, 3",
                "1, 2, 3, 4",
                "1, 2, 3, 4, 5"
        );
        List<Integer> expected = Arrays.asList(3, 6, 10, 15);
        assertEquals(expected, stringCalculatorService.addInBatches(input));
    }

    @Test
    void testLargeInputPerformsEfficiently() {
        String largeInput = IntStream.range(1, 1001).mapToObj(String::valueOf).collect(Collectors.joining(","));
        assertTimeout(Duration.ofSeconds(1), () -> stringCalculatorService.add(largeInput));
    }
}
