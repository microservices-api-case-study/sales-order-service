package com.retail.services.salesorderservice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retail.services.salesorderservice.model.Order;
import com.retail.services.salesorderservice.model.OrderLineItem;
import com.retail.services.salesorderservice.model.SalesOrder;
import com.retail.services.salesorderservice.repos.SalesOrderRepository;

@Service
public class SalesOrderService {
	private static final Logger log = LoggerFactory.getLogger(SalesOrderService.class);

	@Autowired
	ItemService itemService;

	@Autowired
	SalesOrderRepository salesOrderRepository;

	public String createOrder(Order orderRequest) {
		// Creating the sales order
		log.info("Creating a new order...");
		SalesOrder salesOrder = new SalesOrder();
		salesOrder.setCustId(orderRequest.getCustomerId());
		salesOrder.setOrderDate(orderRequest.getOrderDate());
		salesOrder.setOrderDesc(orderRequest.getOrderDescription());
		BigDecimal totalPrice = BigDecimal.ZERO;
		List<OrderLineItem> listOfOrderLineItems = new ArrayList<OrderLineItem>();
		for (Entry<String, Long> item : orderRequest.getItemsWithQtyMap().entrySet()) {
			String itemName = item.getKey();
			Long itemQty = item.getValue();
			OrderLineItem orderLineItem = new OrderLineItem();
			orderLineItem.setItemName(itemName);
			orderLineItem.setItemQty(itemQty);
			orderLineItem.setSalesOrder(salesOrder);
			listOfOrderLineItems.add(orderLineItem);
			totalPrice = totalPrice.add(new BigDecimal(itemQty).multiply(getItemPrice(itemName)));
			log.info(itemName + " has been added to the order.");
		}
		salesOrder.setTotalPrice(totalPrice);
		salesOrder.setListOfOrderLineItems(listOfOrderLineItems);
		salesOrderRepository.save(salesOrder);
		log.info("Order Created.");
		return "Successfully created the order# " + salesOrder.getId() + " with total cost of $"
				+ salesOrder.getTotalPrice();
	}

	public BigDecimal getItemPrice(String itemName) {
		return itemService.getItemByName(itemName).getPrice();
	}

}
