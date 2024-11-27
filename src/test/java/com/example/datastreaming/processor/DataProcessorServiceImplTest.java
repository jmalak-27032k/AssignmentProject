package com.example.datastreaming.processor;

import com.example.datastreaming.model.User;
import com.example.datastreaming.producer.KafkaProducerService;
import com.example.datastreaming.util.AgeCalculator;
import com.example.datastreaming.util.Constant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DataProcessorServiceImplTest {

    @Mock
    private KafkaProducerService kafkaProducerService;

    @Mock
    private AgeCalculator ageCalculator;

    @InjectMocks
    private DataProcessorServiceImpl dataProcessorService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setName("Test User");
        user.setAddress("123 Streets");
        user.setDateOfBirth(LocalDate.of(1990, 1, 1));
    }

    @Test
    void testProcessAndPublish_EvenAge() {
        LocalDate birthDate = LocalDate.of(2000, 1, 1);  // Born on 2000, age will be even in 2024
        user.setDateOfBirth(birthDate);
        when(ageCalculator.calculateAge(user.getDateOfBirth())).thenReturn(24);  // Even age
        dataProcessorService.processAndPublish(user);
        verify(kafkaProducerService, times(1)).sendMessage(Constant.EVEN_TOPIC, user);
        verify(kafkaProducerService, times(0)).sendMessage(Constant.ODD_TOPIC, user);  // Should not send to ODD_TOPIC
    }

    @Test
    void testProcessAndPublish_OddAge() {
        LocalDate birthDate = LocalDate.of(1999, 1, 1);  // Born on 1999, age will be odd in 2024
        user.setDateOfBirth(birthDate);
        when(ageCalculator.calculateAge(user.getDateOfBirth())).thenReturn(25);  // Odd age
        dataProcessorService.processAndPublish(user);
        verify(kafkaProducerService, times(1)).sendMessage(Constant.ODD_TOPIC, user);
        verify(kafkaProducerService, times(0)).sendMessage(Constant.EVEN_TOPIC, user);  // Should not send to EVEN_TOPIC
    }

    @Test
    void testProcessAndPublish_AgeCalculatorCalled() {
        LocalDate birthDate = LocalDate.of(1999, 1, 1);
        user.setDateOfBirth(birthDate);
        when(ageCalculator.calculateAge(user.getDateOfBirth())).thenReturn(25);  // Odd age
        dataProcessorService.processAndPublish(user);
        verify(ageCalculator, times(1)).calculateAge(user.getDateOfBirth());
    }

    @Test
    void testProcessAndPublish_NullUser() {
        dataProcessorService.processAndPublish(null);
        verify(kafkaProducerService, times(0)).sendMessage(anyString(), any());
        verify(ageCalculator, times(0)).calculateAge(any());
    }
}
