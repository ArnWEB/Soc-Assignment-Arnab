package com.soc.restaurantsearchservice.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soc.restaurantsearchservice.exception.RestaurantException;
import com.soc.restaurantsearchservice.model.Restaurant;
import com.soc.restaurantsearchservice.repository.RestaurantRepository;

import lombok.Value;

@Service
@Transactional
public class RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepository;

	private NewTopic topic;

	private KafkaTemplate<String, String> kafkaTemplate;

	ObjectMapper objectMapper = new ObjectMapper();

	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(RestaurantService.class);

//		@Autowired
//		KafkaTemplate<String, Object> kafkatemplate;
//		
//		public void sendMsgToTopic(String msg)
//		{
//			CompletableFuture<SendResult<String, Object>> future=(CompletableFuture<SendResult<String, Object>>) kafkatemplate.send("java",msg);
//			future.whenComplete((res,ex)->{
//				if(ex==null) {
//					System.out.println(msg+" "+res.getRecordMetadata().offset());
//				}
//				else {
//					System.out.println("unable to send"+ex.getMessage());
//				}
//			});
//		}

	public RestaurantService(NewTopic topic, KafkaTemplate<String, String> kafkaTemplate) {
		this.topic = topic;
		this.kafkaTemplate = kafkaTemplate;
	}

	public ResponseEntity<Restaurant> save(Restaurant r) throws RestaurantException {

		if (restaurantRepository.existsById(r.getId())) {
			throw new RestaurantException("Object already exist");
		} else {
			return new ResponseEntity<>(restaurantRepository.save(r), HttpStatus.CREATED);
		}
	}

	public ResponseEntity<Restaurant> getRestaurant(int id) throws RestaurantException {
		if (restaurantRepository.existsById(id)) {
			Optional<Restaurant> rest = restaurantRepository.findById(id);
			return new ResponseEntity<>(rest.get(), HttpStatus.FOUND);
		} else {
			throw new RestaurantException("Restaurant with " + id + " doesnt exist");
		}
	}

	public ResponseEntity<Restaurant> updateRestaurant(int id, Restaurant r) throws RestaurantException {

		if (restaurantRepository.existsById(r.getId())) {
			Restaurant r1 = new Restaurant();
			r1 = restaurantRepository.findById(id).orElse(null);
//			r.getBudget()==r1.getBudget()?r1.setBudget(r.getBudget()):r.setBudget(r.getBudget());
			r1.setBudget(r.getBudget());
			r1.setRestaurantName(r.getRestaurantName());
			r1.setCuisine(r.getCuisine());
			r1.setDistance(r.getDistance());
			r1.setLocation(r.getLocation());
			r1.setMenu(r.getMenu());
			r1.setRating(r.getRating());
			r1.setMenu(r.getMenu());
			restaurantRepository.save(r1);
			return new ResponseEntity<>(r1, HttpStatus.OK);
		} else {
			throw new RestaurantException("You can only update a object if it exists");
		}
	}

	public ResponseEntity<Restaurant> deleteRestaurant(int id) throws RestaurantException {
		if (restaurantRepository.existsById(id)) {
			Restaurant r1 = restaurantRepository.findById(id).get();
			restaurantRepository.deleteById(id);
			return new ResponseEntity<>(r1, HttpStatus.OK);
		} else {
			throw new RestaurantException("You cant delete a object which is not exist");
		}
	}

	public List<Restaurant> findByLocation(String location) {
		return restaurantRepository.findByLocation(location);
	}

	public List<Restaurant> findByRating(float rating) {
		return restaurantRepository.findByRating(rating);
	}

	public List<Restaurant> findByBudget(double budget) {
		return restaurantRepository.findByBudget(budget);
	}

	public List<Restaurant> findByCuisine(String cuisine) {
		return restaurantRepository.findByCuisine(cuisine);
	}

	public List<Restaurant> findByRestaurantName(String restaurantName) {
		return restaurantRepository.findByRestaurantName(restaurantName);
	}

	public List<Restaurant> findByDistance(double distance) {
		return restaurantRepository.findByDistance(distance);
	}

//		@KafkaListener(topics="CapstoneTopic", groupId = "CapstoneTopic")
//		public void listenTopic(String msg)
//		{
//			System.out.println("hi"+msg);
//		}
	public void sendMessage(String message) {
//		Message<String> m = MessageBuilder.withPayload(message).setHeader(KafkaHeaders.TOPIC, topic.name()).build();
		kafkaTemplate.send(topic.name(), message);
		LOGGER.info(message + "is the message produced into kafka broker");
	}

	public String postRestaurant(Restaurant res) {

		String restaurant = "";
		try {
			restaurant = objectMapper.writeValueAsString(res);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		kafkaTemplate.send(topic.name(), restaurant);
		return restaurant;
	}
}
