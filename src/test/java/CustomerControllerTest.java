import com.fasterxml.jackson.databind.ObjectMapper;
import org.CarRental.Controller.CustomerController;
import org.CarRental.CustomerService.CustomerService;
import org.CarRental.Model.CustomerEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    private CustomerEntity customer;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        customer = new CustomerEntity();
        customer.setId(1L);
        customer.setFullName("John Doe");
        customer.setEmail("john@example.com");
        customer.setAge(30);
        customer.setDocumentType("Passport");
        customer.setDocumentNumber("A1234567");
        customer.setDriverLicenceNumber("D123456");
        customer.setYearsOfDriving(5.0);
        customer.setIsAuthorized(true);
    }
    @Test
    void createCustomer_ReturnsCustomer_WhenSuccessful() throws Exception {
        given(customerService.createCustomer(any(CustomerEntity.class))).willReturn(customer);

        mockMvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName", is(customer.getFullName())))
                .andExpect(jsonPath("$.email", is(customer.getEmail())));
    }
    @Test
    void getAllCustomers_ReturnsListOfCustomers_WhenSuccessful() throws Exception {
        List<CustomerEntity> customers = Arrays.asList(customer);

        given(customerService.getAllCustomers()).willReturn(customers);

        mockMvc.perform(get("/api/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].fullName", is(customer.getFullName())));
    }
    @Test
    void updateCustomerEmail_ReturnsOk_WhenUpdateIsSuccessful() throws Exception {
        String newEmail = "john.updated@example.com";

        doNothing().when(customerService).updateCustomerEmail(customer.getId(), newEmail);

        mockMvc.perform(patch("/api/customers/{id}/email", customer.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newEmail)))
                .andExpect(status().isOk());

        verify(customerService).updateCustomerEmail(customer.getId(), newEmail);
    }
    @Test
    void updateCustomerFullName_ReturnsOk_WhenUpdateIsSuccessful() throws Exception {
        String updatedFullName = "Jane Doe Updated";

        doNothing().when(customerService).updateCustomerFullName(customer.getId(), updatedFullName);

        mockMvc.perform(patch("/api/customers/{id}/fullName", customer.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedFullName)))
                .andExpect(status().isOk());

        verify(customerService).updateCustomerFullName(customer.getId(), updatedFullName);
    }
    @Test
    void updateCustomerAge_ReturnsOk_WhenUpdateIsSuccessful() throws Exception {
        int updatedAge = 35;

        doNothing().when(customerService).updateCustomerAge(customer.getId(), updatedAge);

        mockMvc.perform(patch("/api/customers/{id}/age", customer.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedAge)))
                .andExpect(status().isOk());

        verify(customerService).updateCustomerAge(customer.getId(), updatedAge);
    }
    @Test
    void deleteCustomer_ReturnsOk_WhenDeletionIsSuccessful() throws Exception {
        doNothing().when(customerService).deleteCustomer(customer.getId());

        mockMvc.perform(delete("/api/customers/{id}", customer.getId()))
                .andExpect(status().isOk());

        verify(customerService).deleteCustomer(customer.getId());
    }


}

