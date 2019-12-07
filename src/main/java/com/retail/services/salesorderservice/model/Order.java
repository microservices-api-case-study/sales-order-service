package com.retail.services.salesorderservice.model;

import java.util.Date;
import java.util.Map;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class Order {

	@ApiModelProperty(example="New Order")
	@NotNull(message = "Order Description is required")
	@NotBlank(message = "Order Description cannot be empty")
	private String orderDescription;

	@ApiModelProperty(example="2020-12-31")
	@NotNull(message = "Order Date is required")
	@DateTimeFormat(pattern="yyyy-mm-dd")
	@Future(message = "Order Date needs to be in the future")
	private Date orderDate;

	@ApiModelProperty(example="1")
	@NotNull(message = "Customer Id is required")
	private Long customerId;

	@ApiModelProperty(example="{\"Pen\": 1,\"Book\": 2}")
	@NotNull(message = "List of items with their quantity is required")
	@NotEmpty(message = "List of items with their quantity cannot be empty")
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
