package com.incubyte.assessment.services;

import com.incubyte.assessment.domain.model.StringCalculatorInput;
import com.incubyte.assessment.util.string.StringCalculatorUtils;
import com.incubyte.assessment.util.validator.StringCalculatorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StringCalculatorService {
    @Autowired
    StringCalculatorValidator inputValidator;

    public int add(String numbers) {
        inputValidator.validateInput(numbers);
        if (!StringUtils.hasText(numbers)) return 0;

        StringCalculatorInput stringCalculatorInput = StringCalculatorUtils.extractDelimiter(numbers);;
        String delimiter = stringCalculatorInput.delimiter();
        numbers = stringCalculatorInput.numbers();
        String[] numberStrings = numbers.split(delimiter);

        List<Integer> negativeNumbers = new ArrayList<>();
        int sum = Arrays.stream(numberStrings)
                .map(String::strip)
                .filter(number -> {
                    if (inputValidator.isNumeric(number)) {
                        return true;
                    } else {
                        throw new IllegalArgumentException("Other characters found");
                    }
                })
                .mapToInt(Integer::parseInt)
                .peek(value -> {
                    if (value < 0) {
                        negativeNumbers.add(value);
                    }
                })
                .sum();

        if (!negativeNumbers.isEmpty()) {
            throw new IllegalArgumentException("Negative numbers not allowed " + negativeNumbers.stream().map(String::valueOf).collect(Collectors.joining(", ")));
        }

        return sum;
    }

    public List<Integer> addInBatches(List<String> numberBatches) {
        return numberBatches.stream().map(this::add).toList();
    }
}
