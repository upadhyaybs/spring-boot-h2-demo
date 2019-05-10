package com.bsu.restaurant.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.bsu.restaurant.domain.Restaurant;

public final class ObjectMapper {
	
	private ObjectMapper() {}
	
	public static Restaurant toDomain(com.bsu.restaurant.entity.Restaurant entity){
		Restaurant restaurant=new Restaurant();
		BeanUtils.copyProperties(entity, restaurant);
		return restaurant;
	}
	
	
	public static com.bsu.restaurant.entity.Restaurant toEntity(Restaurant restaurant){
		com.bsu.restaurant.entity.Restaurant entity=new com.bsu.restaurant.entity.Restaurant();
		BeanUtils.copyProperties(restaurant, entity);
		return entity;
	}
	
	
	public static List<Restaurant> toDomainList(List<com.bsu.restaurant.entity.Restaurant> source){
		List<Restaurant> target =new ArrayList<>();
		for (com.bsu.restaurant.entity.Restaurant entity : source) {
			Restaurant restaurant=new Restaurant();
			BeanUtils.copyProperties(entity, restaurant);
			target.add(restaurant);
		}
		return target;
	}

}
