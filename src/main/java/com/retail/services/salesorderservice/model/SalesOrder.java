package com.retail.services.salesorderservice.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "sales_order")
public class SalesOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "order_date")
	private Date orderDate;

	@Column(name = "cust_id")
	private Long custId;

	@Column(name = "order_desc")
	private String orderDesc;

	@Column(name = "total_price")
	private BigDecimal totalPrice;

	@OneToMany(mappedBy = "salesOrder", cascade = CascadeType.ALL)
	private List<OrderLineItem> listOfOrderLineItems;

	public SalesOrder() {
	}

	public SalesOrder(Long id, Date orderDate, Long custId, String orderDesc, BigDecimal totalPrice,
			List<OrderLineItem> listOfOrderLineItems) {
		super();
		this.id = id;
		this.orderDate = orderDate;
		this.custId = custId;
		this.orderDesc = orderDesc;
		this.totalPrice = totalPrice;
		this.listOfOrderLineItems = listOfOrderLineItems;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public String getOrderDesc() {
		return orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<OrderLineItem> getListOfOrderLineItems() {
		return listOfOrderLineItems;
	}

	public void setListOfOrderLineItems(List<OrderLineItem> listOfOrderLineItems) {
		this.listOfOrderLineItems = listOfOrderLineItems;
	}

}
