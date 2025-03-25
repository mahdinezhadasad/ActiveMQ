package com.example.activemq.Listner;


import com.example.activemq.config.JmsConfig;
import com.example.activemq.model.HelloWorldMessage;
import jakarta.jms.Message;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;

import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class HelloMessageListner {


    @JmsListener(destination = JmsConfig.MY_QUEUE_NAME)
    public void listner(@Payload HelloWorldMessage helloWorldMessage,
                        @Headers MessageHeaders headers, Message message) {

    System.out.println("I Got a message: " + helloWorldMessage);
        System.out.println(helloWorldMessage);
    }
}
