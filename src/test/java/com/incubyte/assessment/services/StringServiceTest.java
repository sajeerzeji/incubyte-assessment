package com.incubyte.assessment.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class StringServiceTest {
    @Autowired
    StringService stringService;

    @Test
    void testEmptyStringAsInputShouldReturnZero() {
        assertEquals(0, stringService.add(""));
    }

    @Test
    void testCommaSeparatedNumbersInAStringAsInputShouldReturnTheSum() {
        assertEquals(3, stringService.add("1,2"));
    }

    @Test
    void testCommaSeparatedNumbersAndOtherElementsInAStringAsInputShouldReturnError() {
        assertThrows(IllegalArgumentException.class, () -> stringService.add("1,2,Hi"));
    }

    @Test
    void testNumbersSeparatedByNewLineAndCommasAsAStringAsInputShouldReturnTheSum() {
        assertEquals(6, stringService.add("1,2\n3"));
    }

    @Test
    void testNumbersSeparatedByNewLineAndCommasWithSpaceAsAStringAsInputShouldReturnTheSum() {
        assertEquals(6, stringService.add("1, 2\n 3"));
    }

    @Test
    void testNumbersSeparatedByCustomDelimiterAsAStringAsInputPrependedWithDelimiterShouldReturnTheSum() {
        assertEquals(6, stringService.add("//;\n1;2;3"));
    }

    @Test
    void testNumbersSeparatedByCustomDelimiterAsAStringAsInputAndDelimiterDeclareNotAtTheStartOfTheStringShouldReturnError() {
        assertThrows(IllegalArgumentException.class, () -> stringService.add("1;2//;\n;3"));
    }

    @Test
    void testNumbersSeparatedByCustomDelimiterAsAStringAsInputAndDelimiterIsNotDeclaredShouldReturnError() {
        assertThrows(IllegalArgumentException.class, () -> stringService.add("1;2;3"));
    }

    @Test
    void testNumbersSeparatedByCustomDelimiterAsAStringAsInputAndDelimiterIsNotProperlyDeclaredShouldReturnError() {
        assertThrows(IllegalArgumentException.class, () -> stringService.add("//1;2;3"));
    }

    @Test
    void testCommaSeparatedNumbersAlongWithNegativeNumbersInAStringAsInputShouldReturnError() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> stringService.add("1,-8,2,-1"));
        assertEquals("Negative numbers not allowed -8, -1", exception.getMessage());
    }

    @Test
    void testNumbersStringInputsInBatchesAsInputShouldReturnSumInBatches() {
        List<String> input = Arrays.asList(
                "1, 2",
                "1, 2, 3",
                "1, 2, 3, 4",
                "1, 2, 3, 4, 5"
        );
        List<Integer> expected = Arrays.asList(3, 6, 10, 15);
        assertEquals(expected, stringService.addInBatches(input));
    }
}
