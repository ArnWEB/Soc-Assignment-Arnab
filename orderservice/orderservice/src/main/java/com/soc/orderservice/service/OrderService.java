package com.soc.orderservice.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.soc.orderservice.model.Item;
import com.soc.orderservice.model.MenuItem;
import com.soc.orderservice.model.Order;
import com.soc.orderservice.model.Restaurant;
import com.soc.orderservice.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderrepository;

//	@Autowired
//	private ModelMapper modelMapper;

	public Order save(Order order, int rid) {
		RestTemplate restTemplate = new RestTemplate();
		Restaurant r = restTemplate.getForObject("http://restaurant-search-service/restaurant/{rid}", Restaurant.class, rid);
		order.setMenuitem(r.getMenu().getMenuitem());
		double price=r.getMenu().getMenuitem().stream().mapToDouble(i->i.getPrice()).sum();
		order.setPrice(price);
		return orderrepository.save(order);
	}
}
