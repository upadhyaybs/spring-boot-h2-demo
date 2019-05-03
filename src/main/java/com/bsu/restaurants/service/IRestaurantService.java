/**
 * 
 */
package com.bsu.restaurants.service;

import java.util.List;

import com.bsu.restaurants.domain.Restaurant;


/**
 * @author bsu
 *
 */
public interface IRestaurantService {
	
	public Restaurant save(Restaurant entity);
	
	public Restaurant findById(Long id);
	
	public List<Restaurant> findByName(String name);
	
	public List<Restaurant> findByCity(String zipCode);
	
	public List<Restaurant> findByZipCode(String zipCode);
	
	public List<Restaurant> findByState(String state);

}
