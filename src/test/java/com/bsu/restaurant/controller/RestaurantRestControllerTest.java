/**
 * 
 */
package com.bsu.restaurant.controller;

import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bsu.restaurant.controller.RestaurantRestController;
import com.bsu.restaurant.domain.Restaurant;
import com.bsu.restaurant.service.IRestaurantService;


/**
 * @author bsu
 *
 */
@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class RestaurantRestControllerTest {
	
	@Mock
	IRestaurantService service;
	
	@InjectMocks
	RestaurantRestController controller;

	Restaurant restaurant;
	
	List<Restaurant> restaurants;
	
	@BeforeAll
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		restaurant=new Restaurant();
		restaurant.setId(4L);
		restaurant.setName("Taco Bell");
		restaurant.setAddress("1520 Preston Rd");
		restaurant.setCity("Plano");
		restaurant.setState("TX");
		restaurant.setZipCode("75093");
		restaurant.setPhone("800-202-8077");
		restaurant.setEmail("tacobell.plano@gmail.com");
		
		restaurants=new ArrayList<>();
		restaurants.add(restaurant);
	}
	
	@Test
	public void findByIdTest_Found() {
		
		com.bsu.restaurant.domain.Restaurant request=new com.bsu.restaurant.domain.Restaurant();
		request.setId(4L);
		
		when(service.findById(4l)).thenReturn(restaurant);
		ResponseEntity<Restaurant> response=controller.findById(request);
		
		//1. Check response status code
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		
		//2. Check response is not  null or empty
		Assertions.assertNotNull(response.getBody());
		
		//3. Compare ID
		Assertions.assertEquals(request.getId(), response.getBody().getId());
		
	}
	
	@Test
	public void findByIdTest_NotFound() {
		
		com.bsu.restaurant.domain.Restaurant request=new com.bsu.restaurant.domain.Restaurant();
		request.setId(-99l);
		
		when(service.findById(4l)).thenReturn(restaurant);
		ResponseEntity<Restaurant> response=controller.findById(request);
		
		//1. Check response status code
		Assertions.assertEquals(HttpStatus.EXPECTATION_FAILED, response.getStatusCode());
		
	}
	
	@Test
	public void findByIdTest_BadRequest() {
		when(service.findById(4l)).thenReturn(restaurant);
		ResponseEntity<Restaurant> response=controller.findById(null);
		
		//1. Check response status code
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		
	}
	
	
	@Test
	public void findByNameTest_Found() {
		com.bsu.restaurant.domain.Restaurant request=new com.bsu.restaurant.domain.Restaurant();
		request.setName("Taco Bell");
		
		when(service.findByName("Taco Bell")).thenReturn(restaurants);
		ResponseEntity<List<Restaurant>> response=controller.findByName(request);
		
		//1. Check response status code
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		
		//2. Check response is not  null or empty
		Assertions.assertNotNull(response.getBody());
		
		//3.Test equal.
		assertThat(restaurants, is(response.getBody()));
		
		
	}
	
	@Test
	public void findByNameTest_BadRequest() {
		when(service.findByName("Taco Bell")).thenReturn(restaurants);
		ResponseEntity<List<Restaurant>> response=controller.findByName(null);
		
		//1. Check response status code
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		
		
		
	}
	
	
}
