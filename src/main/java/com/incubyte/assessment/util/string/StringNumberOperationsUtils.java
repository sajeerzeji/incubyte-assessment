package com.incubyte.assessment.util.string;

import com.incubyte.assessment.domain.model.StringInput;


public class StringNumberOperationsUtils {
    public static StringInput extractDelimiter(String numbers) {
        if (numbers.startsWith("//")) {
            int delimiterEndIndex = numbers.indexOf("\n");
            if (delimiterEndIndex > 1) {
                numbers = numbers.substring(numbers.indexOf("\n"));
                return new StringInput(numbers, numbers.substring(2, delimiterEndIndex));
            }
        }
        return new StringInput(numbers, "[,\n]");
    }
}
