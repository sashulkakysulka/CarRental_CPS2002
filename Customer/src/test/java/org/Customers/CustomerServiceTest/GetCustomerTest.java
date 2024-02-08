package org.Customers.CustomerServiceTest;

import org.Customers.CarRentalApplication;
import org.Customers.CustomerNotFoundException;
import org.Customers.CustomerService.ICustomerService;
import org.Customers.Model.Customer;
import org.Customers.Repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ContextConfiguration(classes = CarRentalApplication.class)
public class GetCustomerTest {
    @MockBean
    CustomerRepository customerRepository;

    @Autowired
    ICustomerService customerService;

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer.Builder()
                .withFullName("John Doe")
                .withAge(25.0)
                .withEmail("john@example.com")
                .withDocumentNumber("AB1234567")
                .withDocumentType("Passport")
                .withDriverLicenceNumber("DL12345")
                .withYearsOfDriving(3.5)
                .withIsAuthorized(true)
                .withId(98L)
                .build();
    }
    @Test
    void getAllCustomers_ReturnsAllCustomers() {
        when(customerRepository.findAll()).thenReturn(Collections.singletonList(customer));

        List<Customer> customers = customerService.getAllCustomers();

        assertFalse(customers.isEmpty(), "Expected non-empty list of customers");
        assertEquals(1, customers.size(), "Expected list size of 1");
        assertEquals(customer.getId(), customers.get(0).getId(), "Expected customer ID to match");
    }
    @Test
    void getCustomerById_WhenCustomerExists_ReturnsCustomer() {
        when(customerRepository.existsById(customer.getId())).thenReturn(true);
        when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));

        Optional<Customer> foundCustomer = customerService.getCustomerById(customer.getId());

        assertTrue(((Optional<?>) foundCustomer).isPresent(), "Expected customer to be found");
        assertEquals(customer.getId(), foundCustomer.get().getId(), "Expected customer ID to match");
    }
    @Test
    void getCustomerById_WhenCustomerDoesNotExist_ThrowsException() {

        Long nonExistentId = 2L;
        when(customerRepository.existsById(nonExistentId)).thenReturn(false);

        Exception exception = assertThrows(CustomerNotFoundException.class, () -> {
            customerService.getCustomerById(nonExistentId);
        });

        String expectedMessage = "No customer found with ID: " + nonExistentId;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage), "Expected exception message to match");
    }

}
