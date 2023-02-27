package com.amazons3.amazon.kafka.KafkaProducer;

import org.apache.kafka.clients.admin.NewTopic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageProducer.class);
    private NewTopic topic;
    private KafkaTemplate<String ,String> kafkaTemplate;


    public MessageProducer(NewTopic topic, KafkaTemplate<String, String > kafkaTemplate)
    {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String record)
    {
        LOGGER.info(String.format("Message => %s", record));

        Message<String> message = MessageBuilder.withPayload(record)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();

        kafkaTemplate.send(message);
    }

}