package net.javaguides.springbootkafkaturorial.kafka;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "javaguides",groupId = "mygroup")//consume method acts as a subsciber. substriced to this topic. whenevrr prducer will produce a message to the topic, this method will receive the message from the topiuc. we can have any number of subsribers.
    public void consume1(String message) {
        LOGGER.info(String.format("Message Received 1: %s", message));
    }

    @KafkaListener(topics = "javaguides",groupId = "mygroup")//consume method acts as a subsciber. substriced to this topic. whenevrr prducer will produce a message to the topic, this method will receive the message from the topiuc. we can have any number of subsribers.
    public void consume2(String message) {
        LOGGER.info(String.format("Message Received 2: %s", message));
    }
}
