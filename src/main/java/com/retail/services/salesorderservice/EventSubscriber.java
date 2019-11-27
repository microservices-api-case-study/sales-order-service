package com.retail.services.salesorderservice;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class EventSubscriber {
	
	private final Logger log = LoggerFactory.getLogger(EventSubscriber.class);
	
	@Autowired
	CustomerRepository customerRepository;

	@RabbitListener(queues="orderServiceQueue")
	public void handleCustomerCreatedEvent(String message) throws IOException {
		
		Customer customerDetails = new ObjectMapper().readValue(message, Customer.class);
		
		log.info("Message received in Sales Order Service => "+ customerDetails.toString());
		
		customerRepository.save(customerDetails);
		
		log.info("Customer details saved to Table: Customer_SOS");
	}
}
