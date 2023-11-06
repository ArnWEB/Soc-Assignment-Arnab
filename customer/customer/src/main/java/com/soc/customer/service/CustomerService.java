package com.soc.customer.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.soc.customer.model.Customer;
import com.soc.customer.model.MenuItem;
import com.soc.customer.model.Restaurant;
import com.soc.customer.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

	public ResponseEntity<Customer> save(Customer c) {
		return new ResponseEntity<>(customerRepository.save(c), HttpStatus.CREATED);
	}

	public ResponseEntity<Customer> getCustomer(int id) {
		return new ResponseEntity<>(customerRepository.findById(id).get(), HttpStatus.OK);
	}

	public ResponseEntity<Customer> updateCustomer(int id, Customer c) {
		Customer c1 = new Customer();
		c1 = customerRepository.findById(id).get();
		c1.setAddress(c.getAddress());
		c1.setMobile_no(c.getMobile_no());
		c1.setName(c.getName());
		customerRepository.save(c1);
		return new ResponseEntity<>(c1, HttpStatus.OK);
	}

	public ResponseEntity<Customer> deleteCustomer(int id) {
		Customer c1 = new Customer();
		c1 = customerRepository.findById(id).get();
		customerRepository.deleteById(id);
		return new ResponseEntity<>(c1, HttpStatus.OK);
	}

	public ResponseEntity<Restaurant> getRestaurant(int id) {
//			String resourceUrl = "http://localhost:8081/3";
//			Restaurant response  = restTemplate.getForObject("http://localhost:8081/{id}", Restaurant.class,Map.of("id","3"));
//			Restaurant restaurant = response.getBody();
//			logger.info("-------calling getRestaurant in customer service using rest template method--------");
//			return ResponseEntity.ok(restaurant);
//			log.info("-------calling getRestaurant in customer service using rest template method--------");
//			List<MenuItem> m=r.getMenu().getMenuitem();
		RestTemplate restTemplate = new RestTemplate();
		Restaurant r = (Restaurant) restTemplate.getForObject("http://restaurant-search-service/restaurant/{id}", Restaurant.class,
				id);
		return ResponseEntity.ok(r);
	}

	public List<MenuItem> getAllMenuItems(int id) {
		RestTemplate restTemplate = new RestTemplate();
		Restaurant r = (Restaurant) restTemplate.getForObject("http://restaurant-search-service/restaurant/{id}", Restaurant.class,
				id);
		List<MenuItem> m = r.getMenu().getMenuitem();
		return m;
	}

	@KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
	public void listenTopic(String msg) {
//		LOGGER.info(msg + "is the message received from producer");
		System.out.println("yeah received "+msg);
	}
}
