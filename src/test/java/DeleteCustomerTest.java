import org.CarRental.CustomerNotFoundException;
import org.CarRental.CustomerService.CustomerService;
import org.CarRental.Repository.ICustomerRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DeleteCustomerTest {

    @Mock
    private ICustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void deleteCustomer_ValidId() {
        Long customerId = 1L;

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
