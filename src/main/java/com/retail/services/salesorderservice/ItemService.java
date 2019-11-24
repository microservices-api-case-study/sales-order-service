package com.retail.services.salesorderservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "item-service")
@RibbonClient(name = "item-service")
public interface ItemService {

}
