/**
 * 
 */
package com.bsu.restaurants.domain;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author aarna
 *
 */
@Data
@NoArgsConstructor
public class Restaurant {
	
	private Long id;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String address;
	
	@NotEmpty
	private String city;
	
	@NotEmpty
	private String state;
	
	@NotEmpty
	private String zipCode;
	
	private String phone;
	
	private String email;

}
