package com.example.datastreaming.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class AgeCalculatorTest {

    private AgeCalculator ageCalculator;

    @BeforeEach
    void setUp() {
        ageCalculator = new AgeCalculator();  // Initialize the class under test
    }

    @Test
    void testCalculateAge() {
        // Given: A birth date for which we know the age
        LocalDate birthDate = LocalDate.of(2000, 1, 1);  // Age will be 24 in 2024

        // When: Calculate age
        int age = ageCalculator.calculateAge(birthDate);

        // Then: Verify the calculated age is as expected
        assertEquals(24, age);
    }

    @Test
    void testCalculateAge_SameDay() {
        // Given: A birth date that is the same as today's date
        LocalDate birthDate = LocalDate.now();  // Age should be 0

        // When: Calculate age
        int age = ageCalculator.calculateAge(birthDate);

        // Then: Verify the calculated age is 0
        assertEquals(0, age);
    }

    @Test
    void testCalculateAge_LeapYear() {
        // Given: A birth date on a leap year
        LocalDate birthDate = LocalDate.of(2000, 2, 29);  // Born on a leap year

        // When: Calculate age
        int age = ageCalculator.calculateAge(birthDate);

        // Then: Verify the calculated age is correct (should be 24 in 2024)
        assertEquals(24, age);
    }

    @Test
    void testCalculateAge_NullInput() {
        // Given: A null date of birth
        LocalDate birthDate = null;

        // When & Then: Expect an exception to be thrown when passing a null date
        assertThrows(NullPointerException.class, () -> {
            ageCalculator.calculateAge(birthDate);
        });
    }
}
