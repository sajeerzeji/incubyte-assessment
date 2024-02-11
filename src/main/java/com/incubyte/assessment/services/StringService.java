package com.incubyte.assessment.services;

import org.springframework.stereotype.Service;

@Service
public class StringService {
    public int add(String numbers) {
        int sum = 0;
        if (numbers == null) {
            throw new IllegalArgumentException("Null input is not allowed");
        }
        return sum;
    }
}
