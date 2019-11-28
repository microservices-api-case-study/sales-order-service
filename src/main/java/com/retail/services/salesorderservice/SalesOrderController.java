package com.retail.services.salesorderservice;

import static com.retail.services.salesorderservice.util.Constants.MSG_INVALID_CUST_ID;
import static com.retail.services.salesorderservice.util.Constants.MSG_INVALID_ITEM;
import static com.retail.services.salesorderservice.util.Constants.MSG_ORDER_FAILED;
import static com.retail.services.salesorderservice.util.Constants.SINGLE_QUOTE;

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

	/**
	 * Creates the order based on the given inputs
	 * 
	 * @param orderRequest
	 * @return
	 */
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
	
	/**
	 * Returns true if the item is found in the Item table 
	 * which is queried through item-service
	 * 
	 * @param itemName
	 * @return Boolean
	 */
	public Boolean isItemValid(String itemName) {
		log.info("Checking if '" + itemName + "' is valid...");
		try {
			if (itemService.getItemByName(itemName) != null) {
				log.info(itemName + " is valid.");
				return Boolean.TRUE;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
		return Boolean.FALSE;
	}
	
	/**
	 * This is a fallback method
	 * @param orderRequest
	 * @return String
	 */

	public String handlefailedOrder(Order orderRequest) {
		return MSG_ORDER_FAILED;
	}
}
