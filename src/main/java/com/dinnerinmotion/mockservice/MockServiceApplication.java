package com.dinnerinmotion.mockservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.retry.annotation.Backoff;

import java.util.List;

@SpringBootApplication
@EnableEurekaClient
public class MockServiceApplication {
	private final String groupKafka = "mockGroup";

	public static void main(String[] args) {
		SpringApplication.run(MockServiceApplication.class, args);
	}

	@KafkaListener(id = groupKafka, topics = "mockTopic")
	public void processMessage(String message,
							   @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List partitions,
							   @Header(KafkaHeaders.RECEIVED_TOPIC) List topics,
							   @Header(KafkaHeaders.OFFSET) List offsets) {

		System.out.printf("%s-%d[%d] \"%s\"\n", topics.get(0), partitions.get(0), offsets.get(0), message);
	}
}
