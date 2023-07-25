/**
 * 
 */
package com.tp.restaurant.repository;

import java.util.List;

import com.tp.restaurant.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author bsu
 *
 */
@Repository
public interface IRestaurantRepository extends JpaRepository<Restaurant, Long> {
	
	public List<Restaurant> findByName(String name);
	
	public List<Restaurant> findByCity(String city);
	
	public List<Restaurant> findByZipCode(String zipCode);
	
	public List<Restaurant> findByState(String state);

}
