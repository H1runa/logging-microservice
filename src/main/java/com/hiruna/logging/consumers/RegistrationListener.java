package com.hiruna.logging.consumers;

import com.hiruna.logging.utility.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RegistrationListener {
    @KafkaListener(topics = "registration-events", containerFactory = "registrationListenerFactory", groupId = "registration-events-group1")
    public void listener(String msg){
        try{
            Logger.log(msg);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
