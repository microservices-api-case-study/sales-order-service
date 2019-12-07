package com.retail.services.salesorderservice.model;

import java.util.Date;
import java.util.Map;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class Order {

	@NotNull(message = "Order Description is required")
	@NotBlank(message = "Order Description cannot be empty")
	private String orderDescription;

	@NotNull(message = "Order Date is required")
	@DateTimeFormat(pattern="mm/dd/yyyy")
	@Future(message = "Order Date needs to be in the future")
	private Date orderDate;

	@NotNull(message = "Customer Id is required")
	private Long customerId;

	@NotNull(message = "List of items with their quantity is required")
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
