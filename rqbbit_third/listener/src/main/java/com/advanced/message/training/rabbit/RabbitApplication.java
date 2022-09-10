package com.advanced.message.training.rabbit;

import com.advanced.message.training.rabbit.infrastructure.FailProcessor;
import com.advanced.message.training.rabbit.infrastructure.ListenerProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding({ListenerProcessor.class, FailProcessor.class})
public class RabbitApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitApplication.class, args);
	}

}
