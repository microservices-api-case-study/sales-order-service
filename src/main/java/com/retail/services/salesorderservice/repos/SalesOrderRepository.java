package com.retail.services.salesorderservice.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.services.salesorderservice.model.SalesOrder;

public interface SalesOrderRepository extends JpaRepository<SalesOrder, Long> {

}
