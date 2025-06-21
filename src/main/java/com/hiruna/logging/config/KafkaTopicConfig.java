package com.hiruna.logging.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String server_addr;

    @Bean
    public KafkaAdmin kafkaAdmin(){
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, server_addr);
        return new KafkaAdmin(configs);
    }

    //this is manually defining a topic. can add more. its usually better to not let kafka autocreate
    @Bean
    public NewTopic topic1(){
        return new NewTopic("student-events", 1, (short) 1);
    }

    @Bean
    public NewTopic topic2(){
        return new NewTopic("course-events", 1, (short) 1);
    }

    @Bean
    public NewTopic topic3(){
        return new NewTopic("registration-events", 1, (short) 1);
    }
}
