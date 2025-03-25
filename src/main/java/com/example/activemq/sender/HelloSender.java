package com.example.activemq.sender;

import com.example.activemq.config.JmsConfig;
import com.example.activemq.model.HelloWorldMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class HelloSender {

    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper ;
    @Scheduled(fixedRate = 5000)
    public void sendMessage() {
        // ✅ No arguments!
        System.out.println("Sending scheduled message...");
        HelloWorldMessage message = HelloWorldMessage.builder().
                uuid(UUID.randomUUID()).message("Hello World!").build();

        jmsTemplate.convertAndSend(JmsConfig.MY_QUEUE_NAME, message);
    }

    @Scheduled(fixedRate = 8000)
    public void sendAndReceiveMessage() throws JMSException {

        HelloWorldMessage message = HelloWorldMessage.builder()
                .uuid(UUID.randomUUID())
                .message("Hello  Reciver ")
                .build();

       Message revcievedMessage = jmsTemplate.sendAndReceive(JmsConfig.MY_SEND_RCV_QUEUE, session -> {
            try {
                String jsonMessage = objectMapper.writeValueAsString(message);
                TextMessage textMessage = session.createTextMessage(jsonMessage);


                textMessage.setStringProperty("_type", HelloWorldMessage.class.getName());

                System.out.println("🚀 Sending Hello Message: " + jsonMessage);
                return textMessage;

            } catch (JsonProcessingException e) {
                throw new JMSException("Error serializing message: " + e.getMessage());
            }
        });

        System.out.println(revcievedMessage.getBody(HelloWorldMessage.class));
    }

}
