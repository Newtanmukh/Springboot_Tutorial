package com.amazons3.amazon.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazons3.amazon.payloads.PayloadClass;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.ErrorMessage;

import java.io.IOException;
import java.util.List;

public interface ServiceActivators {

     Message<String> fileReader( Message<PayloadClass> payloadClassMessage) throws IOException;
     void replyFinal(Message<List<String>>rows);

     void handleError(ErrorMessage errorMessage);

      void Kafkaroute(Message<String> row);
}