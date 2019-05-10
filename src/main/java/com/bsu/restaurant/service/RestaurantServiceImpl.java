/**
 * 
 */
package com.bsu.restaurant.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsu.restaurant.entity.Restaurant;
import com.bsu.restaurant.mapper.ObjectMapper;
import com.bsu.restaurant.repository.IRestaurantRepository;

/**
 * @author bsu
 *
 */
@Service
public class RestaurantServiceImpl implements IRestaurantService {
	
	IRestaurantRepository repository;
	
	@Autowired
	public RestaurantServiceImpl(IRestaurantRepository repository) {
		this.repository=repository;
	}

	@Override
	public com.bsu.restaurant.domain.Restaurant findById(Long id) {
		Optional<Restaurant> result=repository.findById(id);
		return result.isPresent() ?ObjectMapper.toDomain(result.get()): null;
	}
	
	@Override
	public List<com.bsu.restaurant.domain.Restaurant> findByName(String name) {
		List<Restaurant> result=repository.findByName(name);
		return ObjectMapper.toDomainList(result);
	}

	@Override
	public List<com.bsu.restaurant.domain.Restaurant> findByCity(String city) {
		List<Restaurant> result= repository.findByCity(city);
		return ObjectMapper.toDomainList(result);
	}

	@Override
	public List<com.bsu.restaurant.domain.Restaurant> findByZipCode(String zipCode) {
		List<Restaurant> result=  repository.findByZipCode(zipCode);
		return ObjectMapper.toDomainList(result);
	}

	@Override
	public List<com.bsu.restaurant.domain.Restaurant> findByState(String state) {
		List<Restaurant> result=  repository.findByState(state);
		return ObjectMapper.toDomainList(result);
	}

	@Override
	public com.bsu.restaurant.domain.Restaurant save(com.bsu.restaurant.domain.Restaurant restaurant) {
		Restaurant entity=ObjectMapper.toEntity(restaurant);
		entity=repository.save(entity);
		return ObjectMapper.toDomain(entity);
	}

}
