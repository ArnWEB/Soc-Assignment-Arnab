package com.soc.restaurantsearchservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soc.restaurantsearchservice.model.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

	
			public List<Restaurant> findByLocation(String location);
			
			public List<Restaurant> findByRating(float rating);
			
			public List<Restaurant> findByBudget(double budget);
			
			public List<Restaurant> findByCuisine(String cuisine);
			
			public List<Restaurant> findByRestaurantName(String restaurantName);
			
			public List<Restaurant> findByDistance(double distance);
}
