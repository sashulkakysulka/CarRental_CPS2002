import org.CarRental.Model.CustomerEntity;
import org.CarRental.CustomerService.CustomerService;
import org.CarRental.Repository.ICustomerRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SaveCustomerTest {

    @Mock
    private ICustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;
    @Test
    void testCustomerEntityFullConstructorUsingBuilder() {
        CustomerEntity customer = new CustomerEntity.Builder()
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
        CustomerEntity customer = new CustomerEntity();
        customer.setAge(20);
        customer.setYearsOfDriving(2.0);

        when(customerRepository.save(any(CustomerEntity.class))).thenReturn(customer);

        CustomerEntity savedCustomer = customerService.createCustomer(customer);

        Assertions.assertTrue(savedCustomer.getIsAuthorized());
        verify(customerRepository).save(customer);
    }

    @Test
    void testCreateCustomer_AgeValidDrivingExperienceInvalid() {
        CustomerEntity customer = new CustomerEntity();
        customer.setAge(20);
        customer.setYearsOfDriving(0.5);

        when(customerRepository.save(any(CustomerEntity.class))).thenReturn(customer);

        CustomerEntity savedCustomer = customerService.createCustomer(customer);

        Assertions.assertFalse(savedCustomer.getIsAuthorized());
        verify(customerRepository).save(customer);
    }

    @Test
    void testCreateCustomer_AgeInvalidDrivingExperienceValid() {
        CustomerEntity customer = new CustomerEntity();
        customer.setAge(17);
        customer.setYearsOfDriving(2.0);

        when(customerRepository.save(any(CustomerEntity.class))).thenReturn(customer);

        CustomerEntity savedCustomer = customerService.createCustomer(customer);

        Assertions.assertFalse(savedCustomer.getIsAuthorized());
        verify(customerRepository).save(customer);
    }

    @Test
    void testCreateCustomer_BothAgeAndDrivingExperienceInvalid() {
        CustomerEntity customer = new CustomerEntity();
        customer.setAge(17);
        customer.setYearsOfDriving(0.5);

        when(customerRepository.save(any(CustomerEntity.class))).thenReturn(customer);

        CustomerEntity savedCustomer = customerService.createCustomer(customer);

        Assertions.assertFalse(savedCustomer.getIsAuthorized());
        verify(customerRepository).save(customer);
    }
}
