package com.incubyte.assessment.services;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StringService {
    public int add(String numbers) {
        int sum = 0;
        if (numbers == null) {
            throw new IllegalArgumentException("Null input is not allowed");
        } else if (StringUtils.hasText(numbers)) {
            String delimiter = "[,\n]";
            if (numbers.contains("//") && numbers.contains("\n") && numbers.indexOf("\n") > numbers.indexOf("//")) {
                delimiter = numbers.substring(2, numbers.indexOf("\n"));
                numbers = numbers.substring(numbers.indexOf("\n"));
            }
            List<String> list = Arrays.asList(numbers.split(delimiter));
            List<String> negativeNumbersList = new ArrayList<>();
            sum = list.stream().map(String::strip).filter(number -> {
                if (number.matches("-?\\d+")) { // Checking if numeric or not
                    if (Integer.parseInt(number) < 0) {
                        negativeNumbersList.add(number);
                    }
                    return true;
                } else
                    throw new IllegalArgumentException("The input contains something more than number and delimiter");
            }).mapToInt(Integer::parseInt).sum();

            if (!negativeNumbersList.isEmpty()) {
                throw new IllegalArgumentException("Negative numbers not allowed " + String.join(", ", negativeNumbersList));
            }
        }
        return sum;
    }
}
