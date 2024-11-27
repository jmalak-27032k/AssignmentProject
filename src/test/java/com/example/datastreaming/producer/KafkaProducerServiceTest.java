package com.example.datastreaming.producer;

import com.example.datastreaming.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import org.springframework.kafka.core.KafkaTemplate;
import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
class KafkaProducerServiceTest {

    @Mock
    private KafkaTemplate<String, User> kafkaTemplate;

    @InjectMocks
    private KafkaProducerService kafkaProducerService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setName("Test User");
        user.setAddress("123 Streets");
        user.setDateOfBirth(LocalDate.of(1990, 1, 1));
    }

    @Test
    void testSendMessage() {
        String topic = "test-topic";
        kafkaProducerService.sendMessage(topic, user);
        verify(kafkaTemplate, times(1)).send(topic, user);
    }

    @Test
    void testSendMessage_NullUser() {
        String topic = "test-topic";
        kafkaProducerService.sendMessage(topic, null);
        verify(kafkaTemplate, times(0)).send(topic, null);
    }

    @Test
    void testSendMessage_NullTopic() {
        String topic = null;
        kafkaProducerService.sendMessage(topic, user);
        verify(kafkaTemplate, times(0)).send(topic, user);
    }
}
