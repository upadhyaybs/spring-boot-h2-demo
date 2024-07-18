/**
 * 
 */
package com.tp.restaurant.domain;

import jakarta.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author bsu
 *
 */
@Data
@NoArgsConstructor
public class Restaurant implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

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
