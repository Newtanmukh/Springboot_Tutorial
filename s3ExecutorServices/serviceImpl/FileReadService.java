package com.amazons3.amazon.serviceImpl;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazons3.amazon.kafka.KafkaProducer.MessageProducer;
import com.amazons3.amazon.payloads.PayloadClass;
import com.amazons3.amazon.service.ServiceActivators;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


import org.springframework.integration.annotation.ServiceActivator;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.ErrorMessage;
import org.springframework.messaging.support.MessageBuilder;

import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.List;



@Service
public class FileReadService implements ServiceActivators {


//    @Autowired
//    MyIntegrationConfig config;

    private MessageProducer messageProducer;

    public FileReadService(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    @Override
    @ServiceActivator(inputChannel = "FileReaderChannel", outputChannel = "KafkaChannel")
    public Message<String> fileReader(@Payload Message<PayloadClass> payloadClassMessage) throws IOException {
        String row = "";
        try {
            AmazonS3 client = payloadClassMessage.getPayload().getClient();
            String BucketName = payloadClassMessage.getPayload().getBucket();
            String ObjectName = payloadClassMessage.getPayload().getObject();
            String ESN = payloadClassMessage.getPayload().getEsn();

            S3Object object = client.getObject(BucketName.toString(), ObjectName.toString());
            BufferedReader reader = new BufferedReader(new InputStreamReader(object.getObjectContent()));
            String[] headers = reader.readLine().split(",");

            CSVParser parser = CSVFormat.DEFAULT.withHeader(headers).parse(reader);

            for (CSVRecord record : parser) {
                System.out.println(ObjectName);
                System.out.println(record);
                if (record.get("external_serial_no").equals(ESN)) {
                    row = (record.toString());
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

//              executor.shutdown();
//              executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        Message<String>rows_messages = MessageBuilder.withPayload(row).setHeader("rm","rowsmsgs").build();
        return rows_messages;
    }

    @Override
    @ServiceActivator(inputChannel = "KafkaChannel",outputChannel = "FileLogger")
    public void Kafkaroute(@Payload Message<String> row)
    {
        messageProducer.sendMessage(row.getPayload());
        MessageChannel replyChannel = (MessageChannel)row.getHeaders().getReplyChannel();
        replyChannel.send(row);

    }


    @Override
    @ServiceActivator(inputChannel = "FileLogger",outputChannel = "FinalChanel")
    public void replyFinal(@Payload Message<List<String>> rows)
    {

        MessageChannel replyChannel = (MessageChannel)rows.getHeaders().getReplyChannel();
        replyChannel.send(rows);
    }

    @Override
    @ServiceActivator(inputChannel = "unRecoverableErrorChannel")
    public void handleError(@Payload ErrorMessage errorMessage) {
        System.out.println(String.valueOf(errorMessage));
    }

}