import org.CarRental.CustomerNotFoundException;
import org.CarRental.CustomerService.CustomerService;
import org.CarRental.Model.CustomerEntity;
import org.CarRental.Repository.ICustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateCustomerTest {

    @Mock
    private ICustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    private CustomerEntity existingCustomer;

    @BeforeEach
    void setUp() {
        existingCustomer = new CustomerEntity();
        existingCustomer.setId(1L);
        existingCustomer.setFullName("John Doe");
        existingCustomer.setEmail("john.doe@example.com");
        existingCustomer.setAge(30);
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

}

