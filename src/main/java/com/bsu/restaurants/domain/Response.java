/**
 * 
 */
package com.bsu.restaurants.domain;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
 * @author bsu
 *
 */
@Data
public final class Response<T> {

	@JsonIgnore
	private final Map<String,Object> data=new HashMap<>();
	
	
	public Response (String elementName,T t) {
		setData(elementName, t);
	}
	
	@JsonAnyGetter
	public Map<String,Object> getData() {
		return this.data;
	}
	
	@JsonAnySetter
	private void setData(String key,Object value) {
		this.data.put(key, value);
	}
	
	public Object get(String key) {
		return this.data.get(key);
	}
	
}
