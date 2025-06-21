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

    @KafkaListener(topics = "student-events", groupId = "student-event-group1", containerFactory = "kafkaListenerContainerFactory")
    public void studentEventListener(Student std, @Header(KafkaHeaders.RECEIVED_KEY) String key) throws IOException {
        Date date = new Date();
        String log = switch (key) {
            case "StudentRetrieved" -> "[%tc] [%s] [%s][%d] was retrieved%n";
            case "StudentCreated" -> "[%tc] [%s] [%s][%d] was created%n";
            case "StudentUpdated" -> "[%tc] [%s] [%s][%d] was updated%n";
            case "StudentDeleted" -> "[%tc] [%s] [%s][%d] was deleted%n";
            default -> "Unknown Student Event";
        };

        String formatted_log = String.format(log, date, key, std.getName(), std.getId());

        Logger.log(formatted_log);
    }
}
