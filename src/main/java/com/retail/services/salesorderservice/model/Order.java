package com.retail.services.salesorderservice.model;

import java.util.Date;
import java.util.Map;

public class Order {

	private String orderDescription;
	private Date orderDate;
	private Long customerId;
	private Map<String, Long> itemsWithQtyMap;

	public Order() {
	}

	public Order(String orderDescription, Date orderDate, Long customerId, Map<String, Long> itemsWithQtyMap) {
		super();
		this.orderDescription = orderDescription;
		this.orderDate = orderDate;
		this.customerId = customerId;
		this.itemsWithQtyMap = itemsWithQtyMap;
	}

	public String getOrderDescription() {
		return orderDescription;
	}

	public void setOrderDescription(String orderDescription) {
		this.orderDescription = orderDescription;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Map<String, Long> getItemsWithQtyMap() {
		return itemsWithQtyMap;
	}

	public void setItemsWithQtyMap(Map<String, Long> itemsWithQtyMap) {
		this.itemsWithQtyMap = itemsWithQtyMap;
	}

}
