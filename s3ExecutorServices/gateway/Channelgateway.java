package com.amazons3.amazon.gateway;

import com.amazons3.amazon.payloads.PayloadClass;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.List;

@MessagingGateway
public interface Channelgateway {

    @Gateway(requestChannel = "FileReaderChannel")
    Message<String> ChannelInitializer(Message<PayloadClass> payloadClass);

    @Gateway(requestChannel = "KafkaChannel")
    void KafkaChannel(@Payload Message<List<String>> records);
}