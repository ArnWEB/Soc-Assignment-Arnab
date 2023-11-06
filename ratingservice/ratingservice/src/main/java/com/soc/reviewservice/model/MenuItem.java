package com.soc.reviewservice.model;

import java.io.Serializable;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

@JsonDeserialize
@Getter
@Setter
public class MenuItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	@JsonProperty
	private int id;
//	@JsonProperty
	private String name;
//	@JsonProperty
	private String description;
//	@JsonProperty
	private int price;

}
