package com.dinnerinmotion.mockservice;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mock")
public class Controller {
    private final KafkaTemplate<String, String> kafkaTemplate;

    private final String topic = "mockTopic";

    Controller(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping(path = "/send/{msg}")
    public void send(@PathVariable String msg) {
        this.kafkaTemplate.send(topic, msg);
        System.out.println("Sent sample message [" + msg + "] to " + topic);
    }
}
