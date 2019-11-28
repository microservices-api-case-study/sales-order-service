package com.retail.services.salesorderservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.retail.services.salesorderservice.model.Item;

@FeignClient(name = "${item-service-client.app-name}")
@RibbonClient(name = "${item-service-client.app-name}")
@RequestMapping("/service2")
public interface ItemService {

	@GetMapping("/items/{itemname}")
	public Item getItemByName(@PathVariable("itemname") String itemName);
}
