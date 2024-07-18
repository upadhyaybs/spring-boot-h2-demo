/**
 * 
 */
package com.tp.restaurant.controller;

import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;


import com.tp.restaurant.domain.Restaurant;
import com.tp.restaurant.service.IRestaurantService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;



/**
 * @author bsu
 *
 */
@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(RestaurantRestController.class)
class RestaurantRestControllerTest {
	
	@Mock
	IRestaurantService service;
	
	@InjectMocks
	RestaurantRestController controller;

	Restaurant restaurant;
	
	List<Restaurant> restaurants;
	
	@BeforeAll
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		
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
	void findByIdTest_Found() {

		Restaurant request=new Restaurant();
		request.setId(4L);
		
		when(service.findById(4L)).thenReturn(restaurant);
		ResponseEntity<Restaurant> response=controller.findById(4L);
		
		//1. Check response status code
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		
		//2. Check response is not  null or empty
		Assertions.assertNotNull(response.getBody());
		
		//3. Compare ID
		Assertions.assertEquals(request.getId(), response.getBody().getId());
		
	}

	@Test
	void findByIdTest_Found1() {

		Restaurant request=new Restaurant();
		request.setId(4L);


		when(service.findById(4L)).thenReturn(restaurant);
		ResponseEntity<Restaurant> response=controller.findById(4L);

		//1. Check response status code
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

		//2. Check response is not  null or empty
		Assertions.assertNotNull(response.getBody());

		//3. Compare ID
		Assertions.assertEquals(request.getId(), response.getBody().getId());

	}
	
	@Test
	void findByIdTest_NotFound() {
		
		Restaurant request=new Restaurant();
		request.setId(-99L);
		
		when(service.findById(4L)).thenReturn(restaurant);
		ResponseEntity<Restaurant> response=controller.findById(7L);
		
		//1. Check response status code
		Assertions.assertEquals(HttpStatus.EXPECTATION_FAILED, response.getStatusCode());
		
	}
	
	@Test
	void findByIdTest_BadRequest() {
		when(service.findById(4L)).thenReturn(restaurant);
		ResponseEntity<Restaurant> response=controller.findById(0);
		
		//1. Check response status code
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		
	}
	
	
	@Test
	void findByNameTest_Found() {
		Restaurant request=new Restaurant();
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
	void findByNameTest_BadRequest() {
		when(service.findByName("Taco Bell")).thenReturn(restaurants);
		ResponseEntity<List<Restaurant>> response=controller.findByName(null);
		
		//1. Check response status code
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	@Test
	void findByCityTest_Found() {

		Restaurant request=new Restaurant();
		request.setId(4L);
		request.setCity("Plano");

		List<Restaurant> restaurantList=new ArrayList<>();
		restaurantList.add(restaurant);

		String city="Plano";

		when(service.findByCity(city)).thenReturn(restaurantList);
		ResponseEntity<List<Restaurant>> response=controller.findByCity(request);

		//1. Check response status code
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

		//2. Check response is not  null or empty
		Assertions.assertNotNull(response.getBody());


	}
	
	
}
