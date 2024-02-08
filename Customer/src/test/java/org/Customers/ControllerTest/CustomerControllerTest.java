package org.Customers.ControllerTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.Customers.CarRentalApplication;
import org.Customers.CustomerService.ICustomerService;
import org.Customers.Model.Customer;
import org.Customers.Repository.CustomerRepository;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = CarRentalApplication.class)
@ActiveProfiles("test")
public class CustomerControllerTest {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private ICustomerService userMockService;

    @Autowired
    CustomerRepository repository;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
        Customer customer = new Customer.Builder()
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
        Customer customer1 = new Customer.Builder()
                .withFullName("John Doe")
                .withAge(25.0)
                .withEmail("john@example.com")
                .withDocumentNumber("AB1234567")
                .withDocumentType("Passport")
                .withDriverLicenceNumber("DL12345")
                .withYearsOfDriving(3.5)
                .withIsAuthorized(true)
                .withId(1L)
                .build();
        Customer customer2 = new Customer.Builder()
                .withFullName("John Doe")
                .withAge(25.0)
                .withEmail("john@example.com")
                .withDocumentNumber("AB1234567")
                .withDocumentType("Passport")
                .withDriverLicenceNumber("DL12345")
                .withYearsOfDriving(3.5)
                .withIsAuthorized(true)
                .withId(2L)
                .build();
        repository.save(customer);
        repository.save(customer1);
        repository.save(customer2);
    }

    @Test
    public void createCustomer_ReturnsCustomer_WhenSuccessful() throws JsonProcessingException, JSONException {
        Customer customer = new Customer.Builder()
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

        String expectedResponseBody = om.writeValueAsString(customer);

        String endpoint = "/customers";

        when(userMockService.createCustomer(any(Customer.class))).thenReturn(customer);

        ResponseEntity<String> responseEntity =
                testRestTemplate.postForEntity(endpoint, customer, String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        JSONAssert.assertEquals(expectedResponseBody,responseEntity.getBody(), true);

    }
    @Test
    public void getAllCustomers_ReturnsListOfCustomers_WhenSuccessful() {

        String baseUrl = "http://localhost:" + port + "/customers/all";

        ResponseEntity<Customer[]> responseEntity = testRestTemplate.getForEntity(baseUrl, Customer[].class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();

        assertThat(Objects.requireNonNull(responseEntity.getBody())[0].getFullName()).isEqualTo("Expected FullName");
    }
    @Test
    public void updateCustomerFullName_ReturnsOk_WhenUpdateIsSuccessful() {
        String updatedFullName = "{\"fullName\":\"Jane Doe Updated\"}";
        Long customerId = 1L;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(updatedFullName, headers);

        ResponseEntity<String> response = testRestTemplate.exchange(
                "http://localhost:" + port + "/customers/" + customerId + "/fullName",
                HttpMethod.PATCH,
                entity,
                String.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    @Test
    public void updateCustomerEmail_ReturnsOk_WhenUpdateIsSuccessful() {
        String updatedEmailJson = "{\"email\":\"updated@example.com\"}";
        Long customerId = 1L;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(updatedEmailJson, headers);

        ResponseEntity<String> response = testRestTemplate.exchange(
                "http://localhost:" + port + "/customers/" + customerId + "/email",
                HttpMethod.PATCH,
                entity,
                String.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    @Test
    public void updateCustomerEmail_ReturnsNotFound_WhenCustomerDoesNotExist() {
        String updatedEmailJson = "{\"email\":\"updated@example.com\"}";
        Long nonExistentCustomerId = 9999L;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(updatedEmailJson, headers);

        ResponseEntity<String> response = testRestTemplate.exchange(
                "http://localhost:" + port + "/customers/" + nonExistentCustomerId + "/email",
                HttpMethod.PATCH,
                entity,
                String.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void deleteCustomer_ReturnsOk_WhenDeletionIsSuccessful() {
        Long customerId = 2L;

        ResponseEntity<String> response = testRestTemplate.exchange(
                "http://localhost:" + port + "/customers/" + customerId,
                HttpMethod.DELETE,
                null,
                String.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    @Test
    public void deleteCustomer_ReturnsNotFound_WhenCustomerDoesNotExist() {
        Long nonExistentCustomerId = 9999L;

        ResponseEntity<Void> response = testRestTemplate.exchange(
                "http://localhost:" + port + "/customers/" + nonExistentCustomerId,
                HttpMethod.DELETE,
                null,
                Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}

