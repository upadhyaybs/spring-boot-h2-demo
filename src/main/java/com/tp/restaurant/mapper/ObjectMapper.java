package com.tp.restaurant.mapper;

import java.util.ArrayList;
import java.util.List;

import com.tp.restaurant.entity.Restaurant;
import org.springframework.beans.BeanUtils;

public final class ObjectMapper {
	
	private ObjectMapper() {}
	
	public static com.tp.restaurant.domain.Restaurant toDomain(Restaurant entity){
		com.tp.restaurant.domain.Restaurant restaurant=new com.tp.restaurant.domain.Restaurant();
		BeanUtils.copyProperties(entity, restaurant);
		return restaurant;
	}
	
	
	public static Restaurant toEntity(com.tp.restaurant.domain.Restaurant restaurant){
		Restaurant entity=new Restaurant();
		BeanUtils.copyProperties(restaurant, entity);
		return entity;
	}
	
	
	public static List<com.tp.restaurant.domain.Restaurant> toDomainList(List<Restaurant> source){
		List<com.tp.restaurant.domain.Restaurant> target =new ArrayList<>();
		for (Restaurant entity : source) {
			com.tp.restaurant.domain.Restaurant restaurant=new com.tp.restaurant.domain.Restaurant();
			BeanUtils.copyProperties(entity, restaurant);
			target.add(restaurant);
		}
		return target;
	}

}
