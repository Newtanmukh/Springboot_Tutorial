package com.amazons3.amazon.kafka.KafkaConsumer;


import com.amazons3.amazon.kafka.event_producer.EventMessageProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageConsumer.class);
    EventMessageProcessor eventMessageProcessor;
    @KafkaListener(topics="${spring.kafka.topic.name}",groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String record) throws Exception {
        LOGGER.info("Message => %s",record);
        eventMessageProcessor.register(record);

    }

}