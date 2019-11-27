package com.retail.services.salesorderservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/service3")
public class SalesOrderController {

	@Autowired
	ItemService itemService;

	@GetMapping("/orders")
	@HystrixCommand(fallbackMethod="")
	public String createOrder() {
		System.out.println(itemService.getItemByName("Pen"));
		System.out.println(itemService.getItemByName("Paper"));
		return "success";
	}

}
