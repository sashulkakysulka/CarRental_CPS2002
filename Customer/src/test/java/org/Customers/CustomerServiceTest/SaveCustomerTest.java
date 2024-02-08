package org.Customers.CustomerServiceTest;

import org.Customers.CarRentalApplication;
import org.Customers.Model.Customer;
import org.Customers.CustomerService.ICustomerService;
import org.Customers.Repository.CustomerRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ContextConfiguration(classes = CarRentalApplication.class)
public class SaveCustomerTest {

    @MockBean
    CustomerRepository customerRepository;

    @Autowired
    ICustomerService customerService;

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer.Builder()
                .withFullName("John Doe")
                .withAge(25)
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
    void testCustomerEntityFullConstructorUsingBuilder() {
        Assertions.assertEquals("John Doe", customer.getFullName());
        Assertions.assertEquals(25, customer.getAge());
        Assertions.assertEquals("john@example.com", customer.getEmail());
        Assertions.assertEquals("AB1234567", customer.getDocumentNumber());
        Assertions.assertEquals("Passport", customer.getDocumentType());
        Assertions.assertEquals("DL12345", customer.getDriverLicenceNumber());
        Assertions.assertEquals(3.5, customer.getYearsOfDriving());
        Assertions.assertEquals(98L, customer.getId());
        Assertions.assertTrue(customer.getIsAuthorized());
    }
    @Test
    void testCreateCustomer_AgeAndDrivingExperienceValid() {
        customer.setAge(20);
        customer.setYearsOfDriving(2.0);

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        Customer savedCustomer = customerService.createCustomer(customer);

        Assertions.assertTrue(savedCustomer.getIsAuthorized());
        verify(customerRepository).save(customer);
    }

    @Test
    void testCreateCustomer_AgeValidDrivingExperienceInvalid() {
        customer.setAge(20);
        customer.setYearsOfDriving(0.5);

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        Customer savedCustomer = customerService.createCustomer(customer);

        Assertions.assertFalse(savedCustomer.getIsAuthorized());
        verify(customerRepository).save(customer);
    }

    @Test
    void testCreateCustomer_AgeInvalidDrivingExperienceValid() {
        customer.setAge(17);
        customer.setYearsOfDriving(2.0);

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        Customer savedCustomer = customerService.createCustomer(customer);

        Assertions.assertFalse(savedCustomer.getIsAuthorized());
        verify(customerRepository).save(customer);
    }

    @Test
    void testCreateCustomer_BothAgeAndDrivingExperienceInvalid() {
        customer.setAge(17);
        customer.setYearsOfDriving(0.5);

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        Customer savedCustomer = customerService.createCustomer(customer);

        Assertions.assertFalse(savedCustomer.getIsAuthorized());
        verify(customerRepository).save(customer);
    }
}
