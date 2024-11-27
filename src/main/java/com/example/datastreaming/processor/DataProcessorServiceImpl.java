package com.example.datastreaming.processor;

import com.example.datastreaming.model.User;
import com.example.datastreaming.producer.KafkaProducerService;
import com.example.datastreaming.util.AgeCalculator;
import com.example.datastreaming.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataProcessorServiceImpl implements DataProcessorService{
    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Autowired
    private AgeCalculator ageCalculator;

    public void processAndPublish(User user){
        if(user!=null) {
            int age = ageCalculator.calculateAge(user.getDateOfBirth());

            if (age % 2 == 0) {
                kafkaProducerService.sendMessage(Constant.EVEN_TOPIC, user);
            } else {
                kafkaProducerService.sendMessage(Constant.ODD_TOPIC, user);
            }
        }
    }
}
