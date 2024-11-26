package com.example.datastreaming.producer;

import com.example.datastreaming.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

    public void sendMessage(String topic, User user) {
        kafkaTemplate.send(topic, user);
    }
}

