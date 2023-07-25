/**
 * 
 */
package com.tp.restaurant.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bsu
 *
 */

@Data
@NoArgsConstructor
@Entity
public class Restaurant {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String address;
	
	private String city;
	
	private String state;
	
	private String zipCode;
	
	private String phone;
	
	private String email;

}
