package com.soc.orderservice.model;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

@JsonDeserialize
@Setter
@Getter
public class Menu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	@JsonProperty
	private int id;
//	@JsonProperty
//	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
//	@JoinColumn(name = "menu_id")
	private List<MenuItem> menuitem;

}
