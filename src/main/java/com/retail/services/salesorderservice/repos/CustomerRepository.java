package com.retail.services.salesorderservice.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.services.salesorderservice.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
