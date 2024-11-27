package com.example.datastreaming.consumer;

import com.example.datastreaming.model.User;
import com.example.datastreaming.processor.DataProcessorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.EnableKafka;

import java.time.LocalDate;

@SpringBootTest
@EnableKafka
@ExtendWith(MockitoExtension.class)
class KafkaConsumerServiceTest {

    @Mock
    private DataProcessorService dataProcessorService;

    @InjectMocks
    private KafkaConsumerService kafkaConsumerService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setName("Test User");
        user.setAddress("123 Streets");
        user.setDateOfBirth(LocalDate.of(1990, 1, 1));
    }

    @Test
    void testConsume() {
        kafkaConsumerService.consume(user);
        verify(dataProcessorService, times(1)).processAndPublish(user);
    }

    @Test
    void testConsume_nullUser() {
        kafkaConsumerService.consume(null);
        verify(dataProcessorService, times(0)).processAndPublish(any(User.class));
    }
}
