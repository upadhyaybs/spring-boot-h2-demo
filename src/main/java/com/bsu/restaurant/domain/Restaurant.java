/**
 * 
 */
package com.bsu.restaurant.domain;

import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bsu
 *
 */
@ApiModel(description = "Class representing a restaurant tracked by the application.")
@Data
@NoArgsConstructor
public class Restaurant {
	
	@ApiModelProperty(notes = "Unique identifier of the restaurant. No two persons can have the same id.",  required = true, position = 0)
	private Long id;
	
	@ApiModelProperty(notes = "Name of the restaurant.", example = "Taco Bell", required = true, position = 1)
	@NotEmpty
	private String name;
	
	@ApiModelProperty(notes = "Address of the restaurant.", example = "710 Victor Pl", required = true, position = 2)
	@NotEmpty
	private String address;
	
	@ApiModelProperty(notes = "City of the restaurant.", example = "New York", required = true, position = 3)
	@NotEmpty
	private String city;
	
	@ApiModelProperty(notes = "State code of the restaurant.", example = "TX", required = true, position = 4)
	@NotEmpty
	private String state;
	
	@ApiModelProperty(notes = "Zip code of the restaurant.", example = "75094", required = true, position = 5)
	@NotEmpty
	private String zipCode;
	
	@ApiModelProperty(notes = "Phone no of the restaurant.", example = "772-782-0000", required = true, position = 6)
	private String phone;
	
	@ApiModelProperty(notes = "Email of the restaurant.", example = "abc@gmail.com", required = true, position = 7)
	private String email;

}
