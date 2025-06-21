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
        String log = "[%s] [%s][%d] was retrieved at [%tc]%n";
        String formatted_log = String.format(log, key, std.getName(), std.getId(), date);

        Logger.log(formatted_log);
    }
}
