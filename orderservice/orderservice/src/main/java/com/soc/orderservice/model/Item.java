package com.soc.orderservice.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Item implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	@JsonProperty
	@Id
	private int id;
//	@JsonProperty
	private String name;
//	@JsonProperty
	private String description;
//	@JsonProperty
	private int price;

}