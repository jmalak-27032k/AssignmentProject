package com.example.datastreaming.util;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class AgeCalculator {

    public int calculateAge(LocalDate dateOfBirth) {
        LocalDate now = LocalDate.now();
        return Period.between(dateOfBirth, now).getYears();
    }
}

