package net.javaguides.springbootkafkaturorial.kafka;


import net.javaguides.springbootkafkaturorial.payload.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerJson {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerJson.class);


    @KafkaListener(topics = "javaguides_json",groupId = "mygroup")
    public void consume(User user) {

        LOGGER.info(String.format("JSON Message Received: %s", user.toString()));

    }
}
