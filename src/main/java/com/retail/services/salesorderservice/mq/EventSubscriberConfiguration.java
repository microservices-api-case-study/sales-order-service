package com.retail.services.salesorderservice.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
public class EventSubscriberConfiguration {
	
	@Value("${rabbitmq.exchange-name}")
	String exchangeName;
	
	@Value("${rabbitmq.queue-name}")
	String queueName;
	
	@Value("${rabbitmq.routing-key}")
	String routingKey;
	
	@Bean
	public Exchange eventExchange() {
		return new TopicExchange(exchangeName);
	}

	@Bean
	public Queue queue() {
		return new Queue(queueName);
	}

	@Bean
	public Binding binding(Queue queue, Exchange eventExchange) {
		return BindingBuilder.bind(queue).to(eventExchange).with(routingKey).noargs();
	}

}
