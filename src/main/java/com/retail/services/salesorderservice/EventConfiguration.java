package com.retail.services.salesorderservice;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventConfiguration {
	@Bean
	public Queue queue() {
		return new Queue("CustomerCreated");
	}
}
