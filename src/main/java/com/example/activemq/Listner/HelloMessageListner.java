package com.example.activemq.Listner;


import com.example.activemq.config.JmsConfig;
import com.example.activemq.model.HelloWorldMessage;
import jakarta.jms.Message;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;

import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class HelloMessageListner {


    private final JmsTemplate jmsTemplate;

    public HelloMessageListner(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @JmsListener(destination = JmsConfig.MY_QUEUE_NAME)
    public void listner(@Payload HelloWorldMessage helloWorldMessage,
                        @Headers MessageHeaders headers, Message message) {

    System.out.println("I Got a message: " + helloWorldMessage);
        System.out.println(helloWorldMessage);
    }

    @JmsListener(destination = JmsConfig.MY_SEND_RCV_QUEUE)
    public void listnerForHello(@Payload HelloWorldMessage helloWorldMessage,
                        @Headers MessageHeaders headers, Message message) {
          HelloWorldMessage helloWorlrld = HelloWorldMessage.builder().uuid(UUID.randomUUID()).message("Recieved World").build();
          jmsTemplate.convertAndSend(JmsConfig.MY_SEND_RCV_QUEUE, helloWorlrld);
    }
}
