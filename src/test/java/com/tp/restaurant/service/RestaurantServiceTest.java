/**
 * 
 */
package com.tp.restaurant.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

import com.tp.restaurant.entity.Restaurant;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;


import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import com.tp.restaurant.repository.IRestaurantRepository;


/**
 * @author bsu
 *
 */
@TestInstance(Lifecycle.PER_CLASS)
//@SpringBootTest
//@RunWith(MockitoJUnitRunner.class)
class RestaurantServiceTest {
	
	@Mock
	IRestaurantRepository repository;
	
	@InjectMocks
	RestaurantServiceImpl service;
	
	List<Restaurant> allRestaurants;
	
	List<Restaurant> allTacoBell;
	
	List<Restaurant> allSubway;
	
	List<Restaurant> allPlanoRestaurants;
	
	List<Restaurant> allSameZipRestaurants;
	
	@BeforeAll
	public void setUp()  {
		MockitoAnnotations.openMocks(this);
		
		Restaurant tacoBell1=new Restaurant();
		tacoBell1.setId(4L);
		tacoBell1.setName("Taco Bell");
		tacoBell1.setAddress("1520 Preston Rd");
		tacoBell1.setCity("Plano");
		tacoBell1.setState("TX");
		tacoBell1.setZipCode("75093");
		tacoBell1.setPhone("800-202-8077");
		tacoBell1.setEmail("tacobell.plano@gmail.com");
		
		Restaurant tacoBell2=new Restaurant();
		tacoBell2.setId(5L);
		tacoBell2.setName("Taco Bell");
		tacoBell2.setAddress("1520 BeltLine Rd");
		tacoBell2.setCity("Dallas");
		tacoBell2.setState("TX");
		tacoBell2.setZipCode("75080");
		tacoBell2.setPhone("800-202-8088");
		tacoBell2.setEmail("tacobell.dallas@gmail.com");
		
		Restaurant subway=new Restaurant();
		subway.setId(6L);
		subway.setName("Subway");
		subway.setAddress("1521 Preston Rd");
		subway.setCity("Plano");
		subway.setState("TX");
		subway.setZipCode("75093");
		subway.setPhone("800-202-8098");
		subway.setEmail("subway.plano@gmail.com");

		allRestaurants=Arrays.asList(tacoBell1,tacoBell2,subway);
		allTacoBell=Arrays.asList(tacoBell1,tacoBell2);
		allSubway=Arrays.asList(subway);
		allPlanoRestaurants=Arrays.asList(tacoBell1,subway);
		allSameZipRestaurants=Arrays.asList(tacoBell1,subway);
	}
	
	@AfterAll
    public void after() {
        //clean data
    }
	
	@Test
	void findByIdTest() {
		Restaurant restaurant=new Restaurant();
		restaurant.setId(4L);
		restaurant.setName("Taco Bell");
		restaurant.setAddress("1520 Preston Rd");
		restaurant.setCity("Plano");
		restaurant.setState("TX");
		restaurant.setZipCode("75093");
		restaurant.setPhone("800-202-8077");
		restaurant.setEmail("tacobell.plano@gmail.com");
		
		when(repository.findById(4L)).thenReturn(Optional.of(restaurant));
		com.tp.restaurant.domain.Restaurant result= service.findById(4L);
		
		Assertions.assertNotNull(result);
		assertEquals(4, result.getId());
		assertEquals("Taco Bell",result.getName());
	}

	@Test
	void findById_FailTest() {

		when(repository.findById(1L)).thenReturn(Optional.empty());
		com.tp.restaurant.domain.Restaurant result= service.findById(1L);

		Assertions.assertNull(result);
	}


	@Test
	void findAllTest() {
		List<Restaurant> restaurantList=new ArrayList<>();
		Restaurant restaurant=new Restaurant();
		restaurant.setId(4L);
		restaurant.setName("Taco Bell");
		restaurant.setAddress("1520 Preston Rd");
		restaurant.setCity("Plano");
		restaurant.setState("TX");
		restaurant.setZipCode("75093");
		restaurant.setPhone("800-202-8077");
		restaurant.setEmail("tacobell.plano@gmail.com");

		restaurantList.add(restaurant);

		restaurant=new Restaurant();
		restaurant.setId(1L);
		restaurant.setName("India Chat Cafe");
		restaurant.setAddress("1520 Preston Rd");
		restaurant.setCity("Plano");
		restaurant.setState("TX");
		restaurant.setZipCode("75099");
		restaurant.setPhone("800-202-8088");
		restaurant.setEmail("indiachatcafe.plano@gmail.com");

		when(repository.findAll()).thenReturn(restaurantList);
		List<com.tp.restaurant.domain.Restaurant> result= service.findAll();

		Assertions.assertNotNull(result);
		Assertions.assertTrue( result.size()>0);
	}
	
	@Test
	void findByNameTest() {
		Restaurant tacoBell=new Restaurant();
		tacoBell.setName("Taco Bell");
		
		String nameTacoBell="Taco Bell";
		when(repository.findByName(nameTacoBell)).thenReturn(allTacoBell);
		List<com.tp.restaurant.domain.Restaurant> result=service.findByName(nameTacoBell);
		
		Assertions.assertNotNull(result);
		assertThat(result).hasSize(2).extracting(com.tp.restaurant.domain.Restaurant::getName).contains(nameTacoBell,nameTacoBell);
		
		String nameSubway="Subway";
		when(repository.findByName(nameSubway)).thenReturn(allSubway);
		result=service.findByName(nameSubway);
		
		Assertions.assertNotNull(result);
		assertThat(result).hasSize(1).extracting(com.tp.restaurant.domain.Restaurant::getName).contains(nameSubway);
	}
	
	@Test
	void findByCityTest() {
		String city="Plano";
		
		when(repository.findByCity(city)).thenReturn(allPlanoRestaurants);
		List<com.tp.restaurant.domain.Restaurant> restaurants=service.findByCity(city);
		
		Assertions.assertNotNull(restaurants);
		assertThat(restaurants).hasSize(2).extracting(com.tp.restaurant.domain.Restaurant::getCity).contains(city,city);
	}
	
	@Test
	void findByZipCodeTest() {
		String zip="75093";
		when(repository.findByZipCode(zip)).thenReturn(allSameZipRestaurants);
		List<com.tp.restaurant.domain.Restaurant> restaurants=service.findByZipCode(zip);
		
		Assertions.assertNotNull(restaurants);
		assertThat(restaurants).hasSize(2).extracting(com.tp.restaurant.domain.Restaurant::getZipCode).contains(zip);
	}
	
	@Test
	 void findByStateTest() {
		String state="TX";
		
		when(repository.findByState(state)).thenReturn(allRestaurants);
		List<com.tp.restaurant.domain.Restaurant> restaurants=service.findByState(state);
		
		Assertions.assertNotNull(restaurants);
		assertThat(restaurants).hasSize(3).extracting(com.tp.restaurant.domain.Restaurant::getState).contains(state);
	}
	
	@Test
	 void saveRestaurantTest() {
		Restaurant entity=new Restaurant();
		entity.setId(4L);
		entity.setName("Taco Bell");
		entity.setAddress("1520 Preston Rd");
		entity.setCity("Plano");
		entity.setState("TX");
		entity.setZipCode("75093");
		entity.setPhone("800-202-8077");
		
		com.tp.restaurant.domain.Restaurant restaurant=new com.tp.restaurant.domain.Restaurant();
		
		BeanUtils.copyProperties(entity, restaurant);
		
		when(repository.save(entity)).thenReturn(entity);
		com.tp.restaurant.domain.Restaurant result=  service.save(restaurant);
		
		assertEquals(4, result.getId());
		assertEquals("Taco Bell",result.getName());
	}
	
}
