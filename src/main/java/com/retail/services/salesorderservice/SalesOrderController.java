package com.retail.services.salesorderservice;

import static com.retail.services.salesorderservice.util.Constants.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.retail.services.salesorderservice.model.Order;
import com.retail.services.salesorderservice.repos.CustomerRepository;
import com.retail.services.salesorderservice.repos.OrderLineItemRepository;

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
		if (!customerRepository.existsById(orderRequest.getCustomerId())) {
			log.info("Invalid Customer.");
			return (SINGLE_QUOTE + orderRequest.getCustomerId() + SINGLE_QUOTE + MSG_INVALID_CUST_ID);
		}

		// Validating Items by verifying the table "Item" with "item_name"
		for (String itemName : orderRequest.getItemsWithQtyMap().keySet()) {
			if (!isItemValid(itemName)) {
				log.info("Invalid Item.");
				return (SINGLE_QUOTE + itemName + SINGLE_QUOTE + MSG_INVALID_ITEM);
			}
		}
		// Create sales order and return the order id
		return salesOrderService.createOrder(orderRequest);
	}

	@HystrixCommand(fallbackMethod = "defaultItemValidity")
	public Boolean isItemValid(String itemName) {
		log.info("Checking if '"+itemName+"' is valid...");
		itemService.getItemByName(itemName);
		return (itemService.getItemByName(itemName) != null);
	}

	public Boolean defaultItemValidity(String itemName) {
		log.info("Returning the default item validity as false.");
		return Boolean.FALSE;
	}

	public String handlefailedOrder(Order orderRequest) {
		return MSG_ORDER_FAILED;
	}
}
