package com.dinnerinmotion.mockservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MockController {
    @Autowired
    private KafkaTemplate<Object, Object> kafkaTemplate;

    @PostMapping(path = "/send/{msg}")
    public void sendMsg(@PathVariable String msg) {
        this.kafkaTemplate.send("restaurants", msg);
    }
}
