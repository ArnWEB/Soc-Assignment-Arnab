package com.capstone.customer;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soc.customer.controller.CustomerController;
import com.soc.customer.model.Customer;
import com.soc.customer.service.CustomerService;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest<MenuItem> {

	@MockBean
	private CustomerService customerService;

	@Autowired
	MockMvc mockMvc;

	public static Customer c, c1;
//	public static List<MenuItem> m;
	public MenuItem m1;

	@BeforeAll
	public static void setup() {
		c = new Customer(1, "harsha", "8978702398", "Hyderabd");
		c1 = new Customer(1, "vardhan", "9701291322", "Guntur");
//		m = new ArrayList<MenuItem>();
//		m1=new MenuItem(1,"bajji","spicy",8.9);
//		m.add(new MenuItem(1,"bajji","spicy",8.9));
	}

	@Test
	public void testSave() throws Exception {
		when(customerService.save(c)).thenReturn(new ResponseEntity<>(c, HttpStatus.CREATED));
		mockMvc.perform(post("http://localhost:8082/customer/").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(c))).andExpect(status().isCreated());
	}

	@Test
	public void testGetCustomer() throws Exception {
		when(customerService.getCustomer(c.getId())).thenReturn(new ResponseEntity<>(c, HttpStatus.OK));
		mockMvc.perform(get("http://localhost:8082/customer/1").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(c))).andExpect(status().isOk());
	}

	@Test
	public void testUpdateCustomer() throws Exception {
		when(customerService.updateCustomer(c.getId(), c1)).thenReturn(new ResponseEntity<>(c1, HttpStatus.OK));
		mockMvc.perform(put("http://localhost:8082/customer/1").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(c1))).andExpect(status().isOk());
	}

	@Test
	public void testDeleteCustomer() throws Exception {
		when(customerService.deleteCustomer(c.getId())).thenReturn(new ResponseEntity<>(c, HttpStatus.OK));
		mockMvc.perform(delete("http://localhost:8082/customer/1").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(c))).andExpect(status().isOk());
	}

//	@Test
//	public void getAllMenuItems() throws Exception {
//		when(customerService..getAllMenuItems(1);).thenReturn();
//		mockMvc.perform(get("http://localhost:8082/customer/1").contentType(MediaType.APPLICATION_JSON)
//				.content(new ObjectMapper().writeValueAsString(c))).andExpect(status().isOk());
//	}
}
