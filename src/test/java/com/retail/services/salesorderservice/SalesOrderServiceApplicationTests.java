package com.retail.services.salesorderservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.retail.services.salesorderservice.model.Item;
import com.retail.services.salesorderservice.model.Order;

@ActiveProfiles("junit")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class SalesOrderServiceApplicationTests {

	private static String URL_PREFIX = "http://localhost:";

	@LocalServerPort
	private int port;

	@Autowired
	TestRestTemplate testRestTemplate;

	@MockBean
	ItemService itemService;

	@BeforeEach
	public void setUp() {

		Item testItem = new Item();
		testItem.setPrice(new BigDecimal("100.00"));
		Mockito.when(itemService.getItemByName(Mockito.anyString())).thenReturn(testItem);
	}

	@Test
	void testCreateValidOrder() {

		Order orderRequest = new Order();
		orderRequest.setCustomerId(1L);
		orderRequest.setOrderDate(new Date());
		orderRequest.setOrderDescription("New Order");
		Map<String, Long> itemsWithQtyMap = new HashMap<String, Long>();
		itemsWithQtyMap.put("Pen", 1L);
		itemsWithQtyMap.put("Book", 2L);
		orderRequest.setItemsWithQtyMap(itemsWithQtyMap);
		ResponseEntity<String> response = testRestTemplate.postForEntity(URL_PREFIX + port + "/service3/orders",
				orderRequest, String.class);
		String responseMessage = response.getBody();
		assertEquals(
				"Successfully created the order# 1 with total cost of $300.00",
				responseMessage);

	}
	
	@Test
	void testCreateOrderWithInvalidCustomerId() {

		Order orderRequest = new Order();
		orderRequest.setCustomerId(4L);
		orderRequest.setOrderDate(new Date());
		orderRequest.setOrderDescription("New Order");
		Map<String, Long> itemsWithQtyMap = new HashMap<String, Long>();
		itemsWithQtyMap.put("Pen", 1L);
		itemsWithQtyMap.put("Book", 2L);
		orderRequest.setItemsWithQtyMap(itemsWithQtyMap);
		ResponseEntity<String> response = testRestTemplate.postForEntity(URL_PREFIX + port + "/service3/orders",
				orderRequest, String.class);
		String responseMessage = response.getBody();
		assertEquals(
				"'4' is not a valid Customer Id. Please check the Customer Id and retry.",
				responseMessage);

	}
	
	@Test
	void testCreateOrderWithInvalidItemName() {
		
		Mockito.when(itemService.getItemByName(Mockito.anyString())).thenReturn(null);

		Order orderRequest = new Order();
		orderRequest.setCustomerId(1L);
		orderRequest.setOrderDate(new Date());
		orderRequest.setOrderDescription("New Order");
		Map<String, Long> itemsWithQtyMap = new HashMap<String, Long>();
		itemsWithQtyMap.put("Ball", 1L);
		orderRequest.setItemsWithQtyMap(itemsWithQtyMap);
		ResponseEntity<String> response = testRestTemplate.postForEntity(URL_PREFIX + port + "/service3/orders",
				orderRequest, String.class);
		String responseMessage = response.getBody();
		assertEquals(
				"'Ball' is not present in the inventory. Please remove that item from the order and retry.",
				responseMessage);

	}
	
	@Test
	void testToShowfaultTolerance() {
		
		Mockito.when(itemService.getItemByName(Mockito.anyString())).thenThrow(new RuntimeException());

		Order orderRequest = new Order();
		orderRequest.setCustomerId(1L);
		orderRequest.setOrderDate(new Date());
		orderRequest.setOrderDescription("New Order");
		Map<String, Long> itemsWithQtyMap = new HashMap<String, Long>();
		itemsWithQtyMap.put("Pen", 1L);
		orderRequest.setItemsWithQtyMap(itemsWithQtyMap);
		ResponseEntity<String> response = testRestTemplate.postForEntity(URL_PREFIX + port + "/service3/orders",
				orderRequest, String.class);
		String responseMessage = response.getBody();
		assertEquals(
				"Order creation failed due to technical issues. Please retry or call our customer care @ 1-888-888-8888.",
				responseMessage);

	}

}
