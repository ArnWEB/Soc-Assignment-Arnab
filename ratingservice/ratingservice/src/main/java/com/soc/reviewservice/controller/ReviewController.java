package com.soc.reviewservice.controller;

import java.util.List;
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

import com.soc.reviewservice.model.Restaurant;
import com.soc.reviewservice.model.Review;
import com.soc.reviewservice.service.ReviewService;

@RestController
@RequestMapping("/review")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	@PostMapping("/{cid}/{rid}")
	public ResponseEntity<Review> save(@RequestBody Review r, @PathVariable int cid, @PathVariable int rid) {
		return reviewService.save(r, cid, rid);
	}

	@GetMapping("/allreviews")
	public List<Review> getAllReviews() {
		return reviewService.getAllReviews();
	}
	
	@GetMapping("/getrestaurant/{id}")
	public Restaurant getRestaurant(@PathVariable int id) {
		return reviewService.getRestaurant(id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteReview(@PathVariable int id) {
		reviewService.deleteReview(id);
	}

	@PutMapping("/{id}")
	public Review updateReview(@RequestBody Review review, @PathVariable int id) {
		return reviewService.updateReview(id, review);
	}
}
