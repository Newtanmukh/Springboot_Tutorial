package net.javaguides.springbootkafkaturorial.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic javaguidesTopic(){
        return TopicBuilder.name("javaguides")
                .partitions(10)
                .build();
    }

    @Bean
    public NewTopic javaguidesTopicJSON(){
        return TopicBuilder.name("javaguides_json")
                .partitions(10)
                .build();
    }
}
