package com.retail.services.salesorderservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineItemRepository  extends JpaRepository<OrderLineItem, Long> {

}
