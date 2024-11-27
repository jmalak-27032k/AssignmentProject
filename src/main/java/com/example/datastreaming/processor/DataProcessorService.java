package com.example.datastreaming.processor;

import com.example.datastreaming.model.User;

public interface DataProcessorService {

     void processAndPublish(User user);
}
