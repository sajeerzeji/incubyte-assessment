package com.incubyte.assessment.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}
