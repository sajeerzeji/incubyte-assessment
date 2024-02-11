package com.incubyte.assessment.services;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
            List<String> list = Arrays.asList(numbers.split(delimiter));
            sum = list.stream().filter(number -> {
                if (number.matches("\\d+")) // Checking if numeric or not
                    return true;
                else
                    throw new IllegalArgumentException("The input contains something more than number and delimiter");
            }).mapToInt(Integer::parseInt).sum();
        }
        return sum;
    }
}
