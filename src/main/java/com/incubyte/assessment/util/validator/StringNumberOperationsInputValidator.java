package com.incubyte.assessment.util.validator;

import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class StringNumberOperationsInputValidator {
    public void validateInput(String numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("Input string cannot be null");
        }
    }

    public boolean isNumeric(String number) {
        return Pattern.compile("-?\\d+").matcher(number).matches();
    }
}
