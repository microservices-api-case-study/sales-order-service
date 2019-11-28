package com.retail.services.salesorderservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/service3")
public class SalesOrderController {

	private static final Logger log = LoggerFactory.getLogger(SalesOrderController.class);

	@Autowired
	ItemService itemService;

	@Autowired
	SalesOrderService salesOrderService;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	OrderLineItemRepository orderLineItemRepository;

	@PostMapping("/orders")
	@HystrixCommand(fallbackMethod = "handlefailedOrder")
	public String createOrder(@RequestBody Order orderRequest) {

		// Validating Customer by verifying the table "Customer_SOS" with "cust_id"
		if (customerRepository.existsById(orderRequest.getCustomerId())) {
			log.info("Invalid Customer.");
			return ("'"+orderRequest.getCustomerId()+"'"
					+ " is not a valid Customer Id. Please check the Customer Id and retry.");
		}

		// Validating Items by verifying the table "Item" with "item_name"
		for (String itemName : orderRequest.getItemsWithQtyMap().keySet()) {
			if (!isItemValid(itemName)) {
				log.info("Invalid Item.");
				return ("'"+itemName+"'"
						+ " is not present in the inventory. Please remove that item from the order and retry.");
			}
		}
		// Create sales order and return the order id
		return salesOrderService.createOrder(orderRequest);
	}

	@HystrixCommand(fallbackMethod = "defaultItemValidity")
	public Boolean isItemValid(String itemName) {
		return (itemService.getItemByName(itemName) != null);
	}

	public Boolean defaultItemValidity(String itemName) {
		return Boolean.FALSE;
	}

	public String handlefailedOrder(Order orderRequest) {
		return "Order creation failed due to technical issues. Please retry or call our customer care @ 1-888-888-8888.";
	}
}
