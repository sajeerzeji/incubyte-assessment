package com.incubyte.assessment.util.string;

import com.incubyte.assessment.domain.model.StringCalculatorInput;


public class StringCalculatorUtils {
    public static StringCalculatorInput extractDelimiter(String numbers) {
        if (numbers.startsWith("//")) {
            int delimiterEndIndex = numbers.indexOf("\n");
            if (delimiterEndIndex > 1) {
                numbers = numbers.substring(numbers.indexOf("\n"));
                return new StringCalculatorInput(numbers, numbers.substring(2, delimiterEndIndex));
            }
        }
        return new StringCalculatorInput(numbers, "[,\n]");
    }
}
