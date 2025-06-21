package com.hiruna.logging.consumers;

import com.hiruna.logging.models.Course;
import com.hiruna.logging.utility.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Component
public class CourseConsumer {

    @KafkaListener(topics = "course-events", groupId = "course-event-group1", containerFactory = "courseListenerFactory")
    public void listener(Course course, @Header(KafkaHeaders.RECEIVED_KEY) String key){
        Date date = new Date();
        String log = switch(key){
            case "CourseRetrieved" -> "[%tc] [%s] [%s][%d] was retrieved%n";
            case "CourseUpdated" -> "[%tc] [%s] [%s][%d] was updated%n";
            case "CourseDeleted" -> "[%tc] [%s] [%s][%d] was deleted%n";
            case "CourseCreated" -> "[%tc] [%s] [%s][%d] was created%n";
            default -> "Unknown Course Event%n";
         };
        String log_formatted = String.format(log, date, key, course.getName(), course.getId());
        //logging to file
        try {
            Logger.log(log_formatted);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
