/**
 * 
 */
package com.bsu.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bsu.restaurant.domain.Restaurant;
import com.bsu.restaurant.service.IRestaurantService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * @author bsu
 *
 */
@RestController
@RequestMapping("/restaurant")
@Api(description = "Set of endpoints for Creating, Retrieving, Updating and Deleting of Restaurant.")
public class RestaurantRestController {
	
	IRestaurantService service;
	
	@Autowired
	public  RestaurantRestController(IRestaurantService service) {
		this.service=service;
	}
	
     @PostMapping("/findById")
     @ApiOperation("Returns restaurant based on provided Id in the request payload.")
     public ResponseEntity<Restaurant> findById(@RequestBody Restaurant request){
    	if (request==null || request.getId()==null || request.getId().longValue()==0) {
    		return new ResponseEntity<Restaurant>(HttpStatus.BAD_REQUEST);
    	}
    	Restaurant result=  service.findById(request.getId());
		return new ResponseEntity<Restaurant>(result, result!=null?HttpStatus.OK:HttpStatus.EXPECTATION_FAILED);
     }
     
     
     @PostMapping("/findByName")
     @ApiOperation("Returns list of all restaurant for the matching name")
     public ResponseEntity<List<Restaurant>> findByName(@RequestBody Restaurant request){
    	 if (request==null || request.getName()==null) {
    		return new ResponseEntity<List<Restaurant>>(HttpStatus.BAD_REQUEST);
    	}
    	List<Restaurant> result= (List<Restaurant>) service.findByName(request.getName());
		return new ResponseEntity<List<Restaurant>>( result, HttpStatus.OK);
     }
     
     @PostMapping("/findByCity")
     @ApiOperation("Returns list of all restaurant for the matching city")
     public ResponseEntity<List<Restaurant>> findByCity(@RequestBody Restaurant request){
    	 if (request==null || request.getCity()==null) {
    		return new ResponseEntity<List<Restaurant>>(HttpStatus.BAD_REQUEST);
    	}
    	List<Restaurant> result= (List<Restaurant>) service.findByCity(request.getCity());
		return new ResponseEntity<List<Restaurant>>(result, HttpStatus.OK);
     }
     
     
     @PostMapping("/findByZipCode")
     @ApiOperation("Returns list of all restaurant for the matching zipcode")
     public ResponseEntity<List<Restaurant>> findByZipCode(@RequestBody Restaurant request){
    	 if (request==null || request.getZipCode()==null) {
    		return new ResponseEntity<List<Restaurant>>(HttpStatus.BAD_REQUEST);
    	}
    	List<Restaurant> result= (List<Restaurant>) service.findByZipCode(request.getZipCode());
		return new ResponseEntity<List<Restaurant>>( result, HttpStatus.OK);
     }
     
     
     @PostMapping("/findByState")
     @ApiOperation("Returns list of all restaurant for the matching state")
     public ResponseEntity<List<Restaurant>> findByState(@RequestBody Restaurant request){
    	 if (request==null || request.getState()==null) {
    		return new ResponseEntity<List<Restaurant>>(HttpStatus.BAD_REQUEST);
    	}
    	List<Restaurant> result= (List<Restaurant>) service.findByState(request.getState());
		return new ResponseEntity<List<Restaurant>>(result, HttpStatus.OK);
     }
     
     
     @PutMapping("/saveRestaurant")
     @ApiOperation("Saves/Updates restaurant details in the DB")
     public ResponseEntity<Restaurant> saveRestaurant(@RequestBody Restaurant request){
    	 if (request==null || request.getName()==null||request.getState()==null) {
    		return new ResponseEntity<Restaurant>(HttpStatus.BAD_REQUEST);
    	}
    	Restaurant result= service.save(request);
		return new ResponseEntity<Restaurant>(result, HttpStatus.OK);
     }
     
     
}