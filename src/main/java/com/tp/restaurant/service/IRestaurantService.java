/**
 * 
 */
package com.tp.restaurant.service;

import java.util.List;

import com.tp.restaurant.domain.Restaurant;
import org.springframework.stereotype.Service;


/**
 * @author bsu
 *
 */
@Service
public interface IRestaurantService {
	
	 Restaurant save(Restaurant entity);

	 List<Restaurant> findAll();
	
	 Restaurant findById(Long id);
	
	 List<Restaurant> findByName(String name);
	
	 List<Restaurant> findByCity(String cityName);
	
	 List<Restaurant> findByZipCode(String zipCode);
	
	 List<Restaurant> findByState(String state);

}
