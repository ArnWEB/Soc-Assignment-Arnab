package com.soc.reviewservice.model;

import java.io.Serializable;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

@JsonDeserialize
@Getter
@Setter
public class Restaurant implements Serializable{

	/**
 * 
 */
private static final long serialVersionUID = 1L;
//	@JsonProperty
	private int id;
//	@JsonProperty
	private String restaurantName;
//	@JsonProperty
	private String location;
//	@JsonProperty
	private String cuisine;
//	@JsonProperty
	private double distance;
//	@JsonProperty
	private double budget;
//	@JsonProperty
	private float rating;
//	@OneToOne(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
//	@JoinColumn(name = "menu_id")
//	@JsonProperty
//	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
//    @JsonDeserialize(using = JsonObjectDeserializer.class)
	private Menu menu;	
}