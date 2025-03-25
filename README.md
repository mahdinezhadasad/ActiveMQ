# 📨 Spring Boot + ActiveMQ + JMS Example

This is a minimal example project that demonstrates how to use **Spring Boot**, **ActiveMQ Artemis**, and **JMS** to send and receive messages using a **scheduled task**.

---

## 📦 Features

- Embedded ActiveMQ Artemis broker
- `@Scheduled` task that sends messages every 5 seconds
- `@JmsListener` that receives and prints messages
- Jackson-based message serialization (`MappingJackson2MessageConverter`)
- Clean code with `@Builder`, `@Payload`, `@Headers`, and custom `MessageConverter`
- Uses **in-VM (embedded)** ActiveMQ connector (`vm://0`)

---

## 🚀 Technologies Used

- Java 17+
- Spring Boot 3.4.x
- ActiveMQ Artemis (embedded)
- JMS (Jakarta)
- Lombok (`@Data`, `@Builder`)
- Jackson for JSON message mapping

---

## 📂 Project Structure

src/ └── main/ ├── java/ │ └── com.example.activemq/ │ ├── config/ │ │ └── JmsConfig.java # JMS config & message converter │ ├── model/ │ │ └── HelloWorldMessage.java # Serializable message DTO │ ├── sender/ │ │ └── HelloSender.java # Scheduled sender using JmsTemplate │ ├── listener/ │ │ └── HelloMessageListener.java # Message consumer │ ├── task/ │ │ └── TaskConfig.java # Task Executor Config (for async) │ └── ActiveMqApplication.java # Main app with embedded broker



---

## 🛠 How it works

### 1. Embedded Artemis Broker

```java
ActiveMQServer server = ActiveMQServers.newActiveMQServer(
    new ConfigurationImpl()
        .setPersistenceEnabled(false)
        .setSecurityEnabled(false)
        .addAcceptorConfiguration("invm", "vm://0")
);
server.start();


---

Let me know if you want this in a downloadable `.md` file or if you plan to dockerize this setup – I can help with that too!


