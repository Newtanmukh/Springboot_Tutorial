package com.amazons3.amazon.kafka.event_producer;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.List;

public interface EventMessageProcessor {
    void register(String message) throws Exception;


}