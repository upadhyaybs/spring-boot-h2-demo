/**
 * 
 */
package com.tp.restaurant.service;

import java.util.List;

import com.tp.restaurant.domain.Restaurant;


/**
 * @author bsu
 *
 */
public interface IRestaurantService {
	
	public Restaurant save(Restaurant entity);

	public List<Restaurant> findAll();
	
	public Restaurant findById(Long id);
	
	public List<Restaurant> findByName(String name);
	
	public List<Restaurant> findByCity(String cityName);
	
	public List<Restaurant> findByZipCode(String zipCode);
	
	public List<Restaurant> findByState(String state);

}
