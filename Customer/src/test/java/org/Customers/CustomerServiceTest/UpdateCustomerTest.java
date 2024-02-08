package org.Customers.CustomerServiceTest;

import org.Customers.CarRentalApplication;
import org.Customers.CustomerNotFoundException;
import org.Customers.CustomerService.ICustomerService;
import org.Customers.Model.Customer;
import org.Customers.Repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration(classes = CarRentalApplication.class)
class UpdateCustomerTest {

    @MockBean
    CustomerRepository customerRepository;

    @Autowired
    ICustomerService customerService;

    private Customer existingCustomer;

    @BeforeEach
    void setUp() {
        existingCustomer = new Customer();
        existingCustomer.setId(1L);
        existingCustomer.setFullName("John Doe");
        existingCustomer.setEmail("john.doe@example.com");
        existingCustomer.setAge(30.0);
        existingCustomer.setDocumentType("Passport");
        existingCustomer.setDocumentNumber("A1234567");
        existingCustomer.setDriverLicenceNumber("D1234567");
        existingCustomer.setYearsOfDriving(5.0);
        existingCustomer.setIsAuthorized(true);
    }
    @Test
    void updateCustomerFullName_UpdatesName_WhenCustomerExists() {
        when(customerRepository.existsById(1L)).thenReturn(true);
        doNothing().when(customerRepository).updateCustomerFullName(1L, "Jane Doe Updated");

        customerService.updateCustomerFullName(1L, "Jane Doe Updated");

        verify(customerRepository).updateCustomerFullName(1L, "Jane Doe Updated");
    }
    @Test
    void updateCustomerFullName_ThrowsException_WhenCustomerDoesNotExist() {
        when(customerRepository.existsById(1L)).thenReturn(false);

        assertThrows(CustomerNotFoundException.class, () -> {
            customerService.updateCustomerFullName(1L, "Jane Doe Updated");
        });
    }
    @Test
    public void updateCustomer_ReturnsUpdatedCustomer_WhenSuccessful() {
        when(customerRepository.findById(anyLong())).thenReturn(java.util.Optional.of(existingCustomer));
        when(customerRepository.save(any(Customer.class))).thenReturn(existingCustomer);

        Customer updatedCustomer = customerService.updateCustomer(existingCustomer.getId(), existingCustomer);

        Assertions.assertEquals(existingCustomer.getFullName(), updatedCustomer.getFullName());
        verify(customerRepository).save(any(Customer.class));
    }
    @Test
    public void updateCustomerEmail_ThrowsException_WhenCustomerIdNotFound() {
        Long customerId = 2L;
        String newEmail = "new.email@example.com";

        when(customerRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(CustomerNotFoundException.class, () -> customerService.updateCustomerEmail(customerId, newEmail));
    }

    @Test
    public void updateCustomerEmail_UpdatesEmail_WhenSuccessful() {
        Long customerId = existingCustomer.getId();
        String newEmail = "new.email@example.com";

        when(customerRepository.existsById(customerId)).thenReturn(true);
        doNothing().when(customerRepository).updateCustomerEmail(anyLong(), anyString());

        customerService.updateCustomerEmail(customerId, newEmail);

        verify(customerRepository).updateCustomerEmail(customerId, newEmail);
    }
    @Test
    public void updateCustomerAge_ThrowsException_WhenCustomerIdNotFound() {
        Long customerId = 3L;
        int newAge = 35;

        when(customerRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(CustomerNotFoundException.class, () -> customerService.updateCustomerAge(customerId, newAge));
    }

    @Test
    public void updateCustomerAge_UpdatesAge_WhenSuccessful() {
        Long customerId = existingCustomer.getId();
        int newAge = 35;

        when(customerRepository.existsById(customerId)).thenReturn(true);
        doNothing().when(customerRepository).updateCustomerAge(anyLong(), anyInt());

        customerService.updateCustomerAge(customerId, newAge);

        verify(customerRepository).updateCustomerAge(customerId, newAge);
    }

}

