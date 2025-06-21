package com.hiruna.logging.consumers;

import com.hiruna.logging.models.Student;
import com.hiruna.logging.utility.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

@Component
public class StudentConsumer {

    @KafkaListener(topics = "student-events", groupId = "student-event-group1")
    public void studentEventListener(Student std, @Header(KafkaHeaders.RECEIVED_KEY) String key) throws IOException {
        Date date = new Date();
        String log = switch (key) {
            case "StudentRetrieved" -> "[%s] [%s][%d] was retrieved at [%tc]%n";
            case "StudentCreated" -> "[%s] [%s][%d] was created at [%tc]%n";
            case "StudentUpdated" -> "[%s] [%s][%d] was updated at [%tc]%n";
            case "StudentDeleted" -> "[%s] [%s][%d] was deleted at [%tc]%n";
            default -> "Unknown Student Event";
        };

        String formatted_log = String.format(log, key, std.getName(), std.getId(), date);

        Logger.log(formatted_log);
    }
}
