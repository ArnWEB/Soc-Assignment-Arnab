package com.capstone.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.apache.http.protocol.HTTP;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.google.common.base.Optional;
import com.soc.customer.model.Customer;
import com.soc.customer.repository.CustomerRepository;
import com.soc.customer.service.CustomerService;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class CustomerServiceTest {

	@MockBean
	private CustomerRepository cr;

	@InjectMocks
	private CustomerService cs;

	public static Customer c, c1;

	@BeforeAll
	public static void setup() {
		c = new Customer(1, "harsha", "8978702398", "Hyderabd");
		c1 = new Customer(1, "vardhan", "9701291322", "Guntur");
	}

	@Test
	public void testSave() throws Exception {
		when(cr.save(c)).thenReturn(c);
		ResponseEntity<Customer> response = cs.save(c);
		assertEquals(c, response.getBody());
	}

	@Test
	public void testGetCustomer() {
		when(cr.findById(c.getId())).thenReturn(java.util.Optional.of(c));
		ResponseEntity<Customer> response = cs.getCustomer(c.getId());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void testDeleteCustomer()
	{
		when(cr.findById(c.getId())).thenReturn(java.util.Optional.of(c));
		doNothing().when(cr).deleteById(c.getId());
		ResponseEntity<Customer> response = cs.deleteCustomer(c.getId());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
}
