package com.soc.reviewservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.soc.reviewservice.model.Customer;
import com.soc.reviewservice.model.Restaurant;
import com.soc.reviewservice.model.Review;
import com.soc.reviewservice.repository.ReviewRepository;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;

	public ResponseEntity<Review> save(Review r, int cid, int rid) {
		RestTemplate restTemplate = new RestTemplate();
		Customer customer = restTemplate.getForObject("http://customer-service/customer/{id}", Customer.class, cid);
		Restaurant restaurant = restTemplate.getForObject("http://restaurant-search-service/restaurant/{id}",
				Restaurant.class, rid);
		r.setCustid(customer.getId());
		r.setCname(customer.getName());
		r.setRid(restaurant.getId());
		r.setRname(restaurant.getRestaurantName());
		reviewRepository.save(r);
		return ResponseEntity.ok(r);
	}

	public List<Review> getAllReviews() {
		return reviewRepository.findAll();
	}

	public void deleteReview(int id) {
		reviewRepository.deleteById(id);
	}

	public Review updateReview(int id, Review review) {
		Review r1 = new Review();
		r1 = reviewRepository.findById(id).get();
		r1.setCname(review.getCname());
		r1.setComments(review.getComments());
		r1.setCustid(review.getCustid());
		r1.setRating(review.getRating());
		r1.setRname(review.getRname());
		r1.setRid(review.getRid());
		return reviewRepository.save(r1);
	}

	public Restaurant getRestaurant(int id) {
		RestTemplate restTemplate = new RestTemplate();
		Restaurant restaurant = restTemplate.getForObject("http://restaurant-search-service/restaurant/{id}",
				Restaurant.class, id);
		return restaurant;
	}
}
