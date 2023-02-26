package net.javaguides.springbootkafkaturorial.controller;


import net.javaguides.springbootkafkaturorial.kafka.KafkaProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/kafka")
public class MessageController {


    private KafkaProducer kafkaProducer;

    public MessageController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }


    //QUERY PARAMATER is message here.
    //http:localhost:8080/api/v1/kafka/publish?message=helloworld
    @GetMapping("/publish")
    public ResponseEntity<?>publish(@RequestParam("message") String message){
        kafkaProducer.sendMessage(message);
        return ResponseEntity.ok("Message Sent Successfully to the topic");
    }

}
