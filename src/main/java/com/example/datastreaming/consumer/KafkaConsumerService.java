package com.example.datastreaming.consumer;

import com.example.datastreaming.model.User;
import com.example.datastreaming.processor.DataProcessorService;
import com.example.datastreaming.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @Autowired
    private DataProcessorService dataProcessorService;

    @KafkaListener(topics = Constant.SOURCE_TOPIC, groupId = Constant.GROUP_ID)
    public void consume(User user) {
        dataProcessorService.processAndPublish(user);
    }
}

