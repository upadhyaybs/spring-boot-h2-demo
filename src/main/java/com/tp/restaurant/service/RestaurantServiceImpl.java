/**
 * 
 */
package com.tp.restaurant.service;

import java.util.List;
import java.util.Optional;

import com.tp.restaurant.entity.Restaurant;
import com.tp.restaurant.mapper.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp.restaurant.repository.IRestaurantRepository;

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
	public com.tp.restaurant.domain.Restaurant findById(Long id) {
		Optional<Restaurant> result=repository.findById(id);
		if (result.isPresent()){
			return ObjectMapper.toDomain(result.get());
		}
		return null;
	}
	
	@Override
	public List<com.tp.restaurant.domain.Restaurant> findByName(String name) {
		List<Restaurant> result=repository.findByName(name);
		return ObjectMapper.toDomainList(result);
	}

	@Override
	public List<com.tp.restaurant.domain.Restaurant> findByCity(String city) {
		List<Restaurant> result= repository.findByCity(city);
		return ObjectMapper.toDomainList(result);
	}

	@Override
	public List<com.tp.restaurant.domain.Restaurant> findByZipCode(String zipCode) {
		List<Restaurant> result=  repository.findByZipCode(zipCode);
		return ObjectMapper.toDomainList(result);
	}

	@Override
	public List<com.tp.restaurant.domain.Restaurant> findByState(String state) {
		List<Restaurant> result=  repository.findByState(state);
		return ObjectMapper.toDomainList(result);
	}

	@Override
	public com.tp.restaurant.domain.Restaurant save(com.tp.restaurant.domain.Restaurant restaurant) {
		Restaurant entity=ObjectMapper.toEntity(restaurant);
		entity=repository.save(entity);
		return ObjectMapper.toDomain(entity);
	}

	@Override
	public List<com.tp.restaurant.domain.Restaurant> findAll() {
		List<Restaurant> result=repository.findAll();
		return ObjectMapper.toDomainList(result);
	}

}
