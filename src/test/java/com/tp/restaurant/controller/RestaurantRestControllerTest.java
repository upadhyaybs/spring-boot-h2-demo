/**
 * 
 */
package com.tp.restaurant.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tp.restaurant.domain.Restaurant;
import com.tp.restaurant.service.IRestaurantService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


/**
 * @author bsu
 *
 */
@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class RestaurantRestControllerTest {

	private MockMvc mockMvc;

	@Mock
	private IRestaurantService service;

	@InjectMocks
	private RestaurantRestController controller;

	Restaurant restaurant;
	
	List<Restaurant> restaurants;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		restaurant=new Restaurant();
		restaurant.setId(1L);
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

	private ObjectMapper objectMapper = new ObjectMapper();

	@Test
	@DisplayName("Save Restaurant Test with Bad Payload")
	void testSaveRestaurant_NullRequest() throws Exception {
		mockMvc.perform(put("/api/restaurants")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(null)))
				.andExpect(status().isBadRequest());
	}

	@Test
	@DisplayName("Save Restaurant Test with Null Name")
	void testSaveRestaurant_NullName() throws Exception {

		Restaurant requestWithNullName = new Restaurant(1l,null,
				"1231 Test Ln",  "City 1", "CA",  "12345","test1@email.com","123-223-5660");

		mockMvc.perform(put("/api/restaurants")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(requestWithNullName)))
				.andExpect(status().isBadRequest());
	}

	@Test
	@DisplayName("Save Restaurant Test with Null State")
	void testSaveRestaurant_NullState() throws Exception {
		Restaurant restaurantWithNullState = new Restaurant(1l,"Test Restaurant",
				"1231 Test Ln",  "City 1", null,  "12345","XXXXXXXXXXXXXXX","000000000000");

		mockMvc.perform(put("/api/restaurants")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(restaurantWithNullState)))
				.andExpect(status().isBadRequest());
	}

	@Test
	@DisplayName("Save Restaurant Test with Valid Payload")
	void testSaveRestaurant_ValidRequest() throws Exception {
		Restaurant savedRestaurant = restaurant;
		when(service.save(any(Restaurant.class))).thenReturn(savedRestaurant);

		mockMvc.perform(put("/api/restaurants")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(restaurant)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Taco Bell"));
	}

	@Test
	@DisplayName("List All Restaurants")
	void testListAll() throws Exception {
		List<Restaurant> restaurantList = Arrays.asList(restaurant);
		when(service.findAll()).thenReturn(restaurantList);

		mockMvc.perform(get("/api/restaurants"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(1));
	}

	@Test
	@DisplayName("Find Restaurant By Id")
	void testFindById() throws Exception {
		// Test case 1: Valid ID
		long validId = 1L;
		when(service.findById(validId)).thenReturn(restaurant);

		mockMvc.perform(get("/api/restaurants/id/{id}", validId))
				.andExpect(status().isOk());

		// Test case 2: Invalid ID (0)
		long invalidId = 0L;

		mockMvc.perform(get("/api/restaurants/id/{id}", invalidId))
				.andExpect(status().isBadRequest());

		// Test case 3: Restaurant not found
		long nonExistentId = 999L;
		when(service.findById(nonExistentId)).thenReturn(null);

		mockMvc.perform(get("/api/restaurants/id/{id}", nonExistentId))
				.andExpect(status().isExpectationFailed());
	}

	@Test
	@DisplayName("Find Restaurant By Name")
	void testFindByName() throws Exception {
		// Test case 1: Valid request
		String validName = "Taco Bell";
		List<Restaurant> restaurantList = Arrays.asList(
				new Restaurant(1l,validName,
						"1231 Test Ln",  "City 1", "CA",  "12345","test1@email.com","123-223-5660"),
				new Restaurant(2l,validName,
						"1231 Test Ln",  "City 2", "CA",  "12346","test2@email.com","123-223-5661"));
		when(service.findByName(validName)).thenReturn(restaurantList);

		mockMvc.perform(post("/api/restaurants/name")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(restaurant)))
				.andExpect(status().isOk());

		// Test case 2: Null request
		mockMvc.perform(post("/api/restaurants/name")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(null)))
				.andExpect(status().isBadRequest());

		// Test case 3: Null name in request
		Restaurant requestWithNullName = new Restaurant(1l,null,
				"1231 Test Ln",  "City 1", "CA",  "12345","test1@email.com","123-223-5660");

		mockMvc.perform(post("/api/restaurants/name")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(requestWithNullName)))
				.andExpect(status().isBadRequest());
	}

	@Test
	@DisplayName("Find Restaurant By City")
	void testFindByCity() throws Exception {
		// Test case 1: Null request
		mockMvc.perform(post("/api/restaurants/city")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(null)))
				.andExpect(status().isBadRequest());

		// Test case 2: Null city in request
		Restaurant requestWithNullCity = new Restaurant(1l,"Taco Bell",
				"1231 Test Ln",  null, "CA",  "12345","test1@email.com","123-223-5660");

		mockMvc.perform(post("/api/restaurants/city")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(requestWithNullCity)))
				.andExpect(status().isBadRequest());

		// Test case 3: Valid request
		String cityName = "Plano";
		when(service.findByCity(cityName)).thenReturn(restaurants);

		mockMvc.perform(post("/api/restaurants/city")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(restaurant)))
				.andExpect(status().isOk());
	}


	@Test
	@DisplayName("Find Restaurant By Zip")
	void testFindByZip() throws Exception {
		// Test case 1: Valid ID
		String validZip = "75074";
		when(service.findByZipCode(validZip)).thenReturn(restaurants);

		mockMvc.perform(get("/api/restaurants/zip/{zipCode}", validZip))
				.andExpect(status().isOk());

		// Test case 3: Restaurant not found
		String nonExistentZipCode = "AAA";
		when(service.findByZipCode(nonExistentZipCode)).thenReturn(Collections.emptyList());
		mockMvc.perform(get("/api/restaurants/zip/{zipCode}", nonExistentZipCode))
				.andExpect(status().isNotFound());
	}

	@Test
	@DisplayName("Find Restaurant By State")
	void testFindByState() throws Exception {
		// Test case 1: Valid ID
		String validState = "TX";
		when(service.findByState(validState)).thenReturn(restaurants);
		mockMvc.perform(get("/api/restaurants/state/{state}", validState))
				.andExpect(status().isOk());


		// Test case 3: Restaurant not found
		String nonExistentStateCode = "AAA";
		when(service.findByState(nonExistentStateCode)).thenReturn(Collections.emptyList());

		mockMvc.perform(get("/api/restaurants/state/{state}", nonExistentStateCode))
				.andExpect(status().isNotFound());
	}
}
