package com.bsu.restaurants.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.bsu.restaurants.domain.Restaurant;

public final class ObjectMapper {
	
	private ObjectMapper() {}
	
	public static Restaurant toDomain(com.bsu.restaurants.entity.Restaurant entity){
		Restaurant restaurant=new Restaurant();
		BeanUtils.copyProperties(entity, restaurant);
		return restaurant;
	}
	
	
	public static com.bsu.restaurants.entity.Restaurant toEntity(Restaurant restaurant){
		com.bsu.restaurants.entity.Restaurant entity=new com.bsu.restaurants.entity.Restaurant();
		BeanUtils.copyProperties(restaurant, entity);
		return entity;
	}
	
	
	public static List<Restaurant> toDomainList(List<com.bsu.restaurants.entity.Restaurant> source){
		List<Restaurant> target =new ArrayList<>();
		for (com.bsu.restaurants.entity.Restaurant entity : source) {
			Restaurant restaurant=new Restaurant();
			BeanUtils.copyProperties(entity, restaurant);
			target.add(restaurant);
		}
		return target;
	}

}
