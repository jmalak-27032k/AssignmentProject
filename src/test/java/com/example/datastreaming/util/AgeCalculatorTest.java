package com.example.datastreaming.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class AgeCalculatorTest {

    private AgeCalculator ageCalculator;

    @BeforeEach
    void setUp() {
        ageCalculator = new AgeCalculator();
    }

    @Test
    void testCalculateAge() {
        LocalDate birthDate = LocalDate.of(2000, 1, 1);
        int age = ageCalculator.calculateAge(birthDate);
        assertEquals(24, age);
    }

    @Test
    void testCalculateAge_SameDay() {
        LocalDate birthDate = LocalDate.now();
        int age = ageCalculator.calculateAge(birthDate);
        assertEquals(0, age);
    }

    @Test
    void testCalculateAge_LeapYear() {
        LocalDate birthDate = LocalDate.of(2000, 2, 29);
        int age = ageCalculator.calculateAge(birthDate);
        assertEquals(24, age);
    }

    @Test
    void testCalculateAge_NullInput() {
        LocalDate birthDate = null;
        assertThrows(NullPointerException.class, () -> {
            ageCalculator.calculateAge(birthDate);
        });
    }
}
