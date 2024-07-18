/**
 * 
 */
package com.tp.restaurant.controller;

import java.util.List;

import com.tp.restaurant.service.IRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tp.restaurant.domain.Restaurant;


/**
 * @author bsu
 *
 */
@RestController
@RequestMapping("/api/restaurants")
public class RestaurantRestController {
	
	IRestaurantService service;
	
	@Autowired
	public  RestaurantRestController(IRestaurantService service) {
		this.service=service;
	}

	@PutMapping
	public ResponseEntity<Restaurant> saveRestaurant(@RequestBody Restaurant request){
		if (request==null || request.getName()==null||request.getState()==null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Restaurant result= service.save(request);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<Restaurant>> listAll(){
		List<Restaurant> result=  service.findAll();
		return new ResponseEntity<>( result, HttpStatus.OK);
	}

     @GetMapping("/id/{id}")
     public ResponseEntity<Restaurant> findById(@PathVariable long id){
    	if ( id==0) {
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    	Restaurant result=  service.findById(id);
		return new ResponseEntity<>(result, result!=null?HttpStatus.OK:HttpStatus.EXPECTATION_FAILED);
     }

     @PostMapping("/name")
     public ResponseEntity<List<Restaurant>> findByName(@RequestBody Restaurant request){
    	 if (request==null || request.getName()==null) {
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    	List<Restaurant> result=  service.findByName(request.getName());
		return new ResponseEntity<>( result, HttpStatus.OK);
     }
     
     @PostMapping("/city")
     public ResponseEntity<List<Restaurant>> findByCity(@RequestBody Restaurant request){
    	 if (request==null || request.getCity()==null) {
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    	List<Restaurant> result=  service.findByCity(request.getCity());
		return new ResponseEntity<>(result, HttpStatus.OK);
     }

     @GetMapping("/zip/{zipCode}")
     public ResponseEntity<List<Restaurant>> findByZipCode(@PathVariable String zipCode){
    	 if (zipCode==null) {
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    	List<Restaurant> result= service.findByZipCode(zipCode);
		return new ResponseEntity<>( result, HttpStatus.OK);
     }

     @GetMapping("/state/{state}")
     public ResponseEntity<List<Restaurant>> findByState(@PathVariable String state){
    	 if (state==null) {
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    	List<Restaurant> result=  service.findByState(state);
		return new ResponseEntity<>(result, HttpStatus.OK);
     }


     
     
}
