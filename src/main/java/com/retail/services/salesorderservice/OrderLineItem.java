package com.retail.services.salesorderservice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_line_item")
public class OrderLineItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "item_name")
	private String itemName;

	@Column(name = "item_quantity")
	private Long itemQty;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private SalesOrder salesOrder;

	public OrderLineItem() {
	}

	public OrderLineItem(Long id, String itemName, Long itemQty, SalesOrder salesOrder) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.itemQty = itemQty;
		this.salesOrder = salesOrder;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Long getItemQty() {
		return itemQty;
	}

	public void setItemQty(Long itemQty) {
		this.itemQty = itemQty;
	}

	public SalesOrder getSalesOrder() {
		return salesOrder;
	}

	public void setSalesOrder(SalesOrder salesOrder) {
		this.salesOrder = salesOrder;
	}

}
