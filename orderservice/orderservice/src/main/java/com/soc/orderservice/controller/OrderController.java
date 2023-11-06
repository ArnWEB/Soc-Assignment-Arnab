package com.soc.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soc.orderservice.model.Order;
import com.soc.orderservice.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderservice;
	
	@PostMapping("/{rid}")
	public Order save(@RequestBody Order order, @PathVariable int rid) {
		return orderservice.save(order,rid);
	}
	
}
