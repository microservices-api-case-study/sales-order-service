package com.retail.services.salesorderservice.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.services.salesorderservice.model.OrderLineItem;

public interface OrderLineItemRepository  extends JpaRepository<OrderLineItem, Long> {

}
