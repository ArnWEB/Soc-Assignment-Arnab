package com.soc.customer.model;

import java.io.Serializable;
import java.util.List;
//import javax.persistence.CascadeType;
//import javax.persistence.FetchType;
//import javax.persistence.OneToMany;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

//@Data
@Getter
@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//@EqualsAndHashCode
//@JsonIgnoreProperties
@JsonDeserialize
public class Menu implements Serializable{

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