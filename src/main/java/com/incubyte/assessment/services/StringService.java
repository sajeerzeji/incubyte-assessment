package com.incubyte.assessment.services;

import com.incubyte.assessment.domain.model.StringInput;
import com.incubyte.assessment.util.string.StringNumberOperationsUtils;
import com.incubyte.assessment.util.validator.StringNumberOperationsInputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class StringService {
    @Autowired
    StringNumberOperationsInputValidator inputValidator;

    public int add(String numbers) {
        inputValidator.validateInput(numbers);
        if (!StringUtils.hasText(numbers)) return 0;

        StringInput stringInput = StringNumberOperationsUtils.extractDelimiter(numbers);;
        String delimiter = stringInput.delimiter();
        numbers = stringInput.numbers();
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
}
