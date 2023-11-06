package com.soc.customer.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soc.customer.model.Customer;
import com.soc.customer.model.MenuItem;
import com.soc.customer.model.Restaurant;
import com.soc.customer.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	private static Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@PostMapping("/")
	public ResponseEntity<Customer> save(@RequestBody Customer c) {
		logger.info("-------customer save method--------");
		return customerService.save(c);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable int id) {
		logger.info("-------getCustomer method--------");
		return customerService.getCustomer(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable int id, @RequestBody Customer c) {
		logger.info("-------updateCustomer method--------");
		return customerService.updateCustomer(id, c);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable int id) {
		logger.info("-------deleteCustomer method--------");
		return customerService.deleteCustomer(id);
	}

	@GetMapping("/restaurant/{id}")
	public ResponseEntity<Restaurant> getRestaurant(@PathVariable int id) {

		logger.info("-------calling getRestaurant in customer service using rest template method--------");
		return customerService.getRestaurant(id);

	}

	@GetMapping("/menuitems/{id}")
	public List<MenuItem> getAllMenuItems(@PathVariable int id) {
		logger.info(
				"-------calling getRestaurant in customer service and extracting menu items using rest template method--------");
		return customerService.getAllMenuItems(id);
	}
}
