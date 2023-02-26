package net.javaguides.springbootkafkaturorial.controller;

import net.javaguides.springbootkafkaturorial.kafka.JsonKafkaProducer;
import net.javaguides.springbootkafkaturorial.payload.User;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/kafka")
public class jsonMessageController {
    private JsonKafkaProducer KafkaProducer;


    //since this has only one parameterised constructor, spring will automatically create a new instance of the class and inject it. so no need to use autowire.
    public jsonMessageController(JsonKafkaProducer jsonKafkaProducer) {
        this.KafkaProducer = jsonKafkaProducer;
    }

    @PostMapping("/publish")
    public ResponseEntity<String >publish(@RequestBody User user)
    {
        KafkaProducer.sendMessage(user);
        return ResponseEntity.ok(String.format("JSON Message send to kafka topic"));
    }

}
