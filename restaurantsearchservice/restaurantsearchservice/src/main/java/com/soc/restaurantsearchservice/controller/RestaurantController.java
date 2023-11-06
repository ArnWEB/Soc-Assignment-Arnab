package com.soc.restaurantsearchservice.controller;

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

import com.soc.restaurantsearchservice.exception.RestaurantException;
import com.soc.restaurantsearchservice.model.Restaurant;
import com.soc.restaurantsearchservice.service.RestaurantService;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;

	private static Logger logger = LoggerFactory.getLogger(RestaurantController.class);

	@PostMapping("/")
	public ResponseEntity<Restaurant> save(@RequestBody Restaurant r) throws RestaurantException {
		logger.info("-------Restaurant save method--------");
		return restaurantService.save(r);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Restaurant> getRestaurant(@PathVariable int id) throws RestaurantException {
		logger.info("-------Restaurant getRestaurant method--------");

		ResponseEntity<Restaurant> rest = restaurantService.getRestaurant(id);
		return rest;
	}

	@PutMapping("/{id}")
	public ResponseEntity<Restaurant> updateRestaurant(@PathVariable int id, @RequestBody Restaurant r)
			throws RestaurantException {
		logger.info("-------Restaurant updateRestaurant method--------");
		return restaurantService.updateRestaurant(id, r);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Restaurant> deleteRestaurant(@PathVariable int id) throws RestaurantException {
		logger.info("-------Restaurant deleteRestaurant method--------");
		return restaurantService.deleteRestaurant(id);
	}

	@GetMapping("/location/{location}")
	public List<Restaurant> findByLocation(@PathVariable String location) {
		logger.info("-------Restaurant findByLocation method--------");
		return restaurantService.findByLocation(location);
	}

	@GetMapping("/rating/{rating}")
	public List<Restaurant> findByRating(@PathVariable float rating) {
		logger.info("-------Restaurant findByRating method--------");
		return restaurantService.findByRating(rating);
	}

	@GetMapping("/budget/{budget}")
	public List<Restaurant> findByBudget(@PathVariable double budget) {
		logger.info("-------Restaurant findByBudget method--------");
		return restaurantService.findByBudget(budget);
	}

	@GetMapping("/cuisine/{cuisine}")
	public List<Restaurant> findByCuisine(@PathVariable String cuisine) {
		logger.info("-------Restaurant findByCuisine method--------");
		return restaurantService.findByCuisine(cuisine);
	}

	@GetMapping("/name/{name}")
	public List<Restaurant> findByRestaurantName(@PathVariable String name) {
		logger.info("-------Restaurant findByRestaurantName method--------");
		return restaurantService.findByRestaurantName(name);
	}

	@GetMapping("/distance/{distance}")
	public List<Restaurant> findByDistance(@PathVariable double distance) {
		logger.info("-------Restaurant findByDistance method--------");
		return restaurantService.findByDistance(distance);
	}

	@PostMapping("/kafka")
	public ResponseEntity<?> getMsg(@RequestBody String msg) {
		restaurantService.sendMessage(msg);
		return ResponseEntity.ok("sent successfully");
	}
	
	@PostMapping("/kafkajson")
	public String postRestaurantUsingKafka(@RequestBody Restaurant res)
	{
		return restaurantService.postRestaurant(res);
	}
}
