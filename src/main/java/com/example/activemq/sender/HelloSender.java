package com.example.activemq.sender;

import com.example.activemq.config.JmsConfig;
import com.example.activemq.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class HelloSender {

    private final JmsTemplate jmsTemplate;

    @Scheduled(fixedRate = 5000)
    public void sendMessage() {
        // ✅ No arguments!
        System.out.println("Sending scheduled message...");
        HelloWorldMessage message = HelloWorldMessage.builder().
                uuid(UUID.randomUUID()).message("Hello World!").build();

        jmsTemplate.convertAndSend(JmsConfig.MY_QUEUE_NAME, message);
    }
}
