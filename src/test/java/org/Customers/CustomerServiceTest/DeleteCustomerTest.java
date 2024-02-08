package org.Customers.CustomerServiceTest;

import org.Customers.CarRentalApplication;
import org.Customers.CustomerNotFoundException;
import org.Customers.CustomerService.ICustomerService;
import org.Customers.Repository.CustomerRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = CarRentalApplication.class)
class DeleteCustomerTest {

    @MockBean
    CustomerRepository customerRepository;

    @Autowired
    ICustomerService customerService;

    @Test
    void deleteCustomer_ValidId() {
        Long customerId = 1L;
        when(customerRepository.existsById(customerId)).thenReturn(true);

        doNothing().when(customerRepository).deleteById(customerId);

        customerService.deleteCustomer(customerId);

        verify(customerRepository).deleteById(customerId);
    }

    @Test
    void deleteCustomer_ThrowsException_WhenCustomerNotFound() {
        Long nonExistentCustomerId = 999L;

        when(customerRepository.existsById(nonExistentCustomerId)).thenReturn(false);

        Exception exception = assertThrows(CustomerNotFoundException.class, () -> {
            customerService.deleteCustomer(nonExistentCustomerId);
        });

        String expectedMessage = "No customer found with ID: " + nonExistentCustomerId;
        assertEquals(expectedMessage, exception.getMessage());

        verify(customerRepository, never()).deleteById(nonExistentCustomerId);
    }
}
