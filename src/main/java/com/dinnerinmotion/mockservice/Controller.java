package com.dinnerinmotion.mockservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mock")
public class Controller {
    @Autowired
    private KafkaTemplate<Object, Object> template;

    @GetMapping(path = "/send/{what}")
    public void sendFoo(@PathVariable String what) {
        this.template.send("mockTopic", what);
    }
}
