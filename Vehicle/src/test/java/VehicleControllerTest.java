
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import org.Vehicle.Model.*;
import org.Vehicle.VehicleService.VehicleService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Nested
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class VehicleControllerTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private VehicleService vehicleMockService;

    @LocalServerPort
    private int port;
/*
    @Test
    void contextLoads() {
    }

   @Test
    public void testCreateVehicle() {
        List<DateRange> listOfDates = new ArrayList<>();
        DateRange range = new DateRange(LocalDate.of(2024,1,1), LocalDate.of(2024,1,5));
        listOfDates.add(range);
        Vehicle vehicle = new Vehicle("type", "model", 24.50, 12.50 );
        vehicle.setDatesRented(listOfDates);
        ResponseEntity<Vehicle> expectedVehicle = new ResponseEntity<>(vehicle, HttpStatus.CREATED);
        String endpoint = "/vehicles";


        ResponseEntity<ResponseEntity> responseEntity = testRestTemplate.postForEntity(endpoint, vehicle, ResponseEntity.class);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }
@Test
    public void testGetAllVehicles(){
        String url = "http://localhost:" + port + "/vehicles/all";

        ResponseEntity<Vehicle[]> responseEntity = testRestTemplate.getForEntity(url, Vehicle[].class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
    }
@Test
    public void testSetPrice(){

        double expectedPrice = 23.40;
        long vehicleId = 1L;

        String url = "http://localhost:" + port + "/vehicles/{vehicleID}";

        ResponseEntity<Vehicle> originalResponse = testRestTemplate.getForEntity(url, Vehicle.class, vehicleId);
        Vehicle originalVehicle = originalResponse.getBody();

        Vehicle updatedVehicle = new Vehicle();
        updatedVehicle.setVehicleId(vehicleId);
        updatedVehicle.setPricePerDay(expectedPrice);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Vehicle> requestEntity = new HttpEntity<>(updatedVehicle, headers);

        ResponseEntity<Vehicle> response = testRestTemplate.postForEntity(url, requestEntity, Vehicle.class, vehicleId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
@Test
    public void testSetCost() {

        double expectedCost = 40.20;
        long vehicleId = 1L;

        String url = "http://localhost:" + port + "/vehicles/{vehicleID}";

        ResponseEntity<Vehicle> originalResponse = testRestTemplate.getForEntity(url, Vehicle.class, vehicleId);
        Vehicle originalVehicle = originalResponse.getBody();

        Vehicle updatedVehicle = new Vehicle();
        updatedVehicle.setVehicleId(vehicleId);
        updatedVehicle.setCostsPerDay(expectedCost);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Vehicle> requestEntity = new HttpEntity<>(updatedVehicle, headers);

        ResponseEntity<Vehicle> response = testRestTemplate.postForEntity(url, requestEntity, Vehicle.class, vehicleId);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testDeleteVehicle(){
        long vehicleId = 1L;

        ResponseEntity<String> response = testRestTemplate.exchange("http://localhost:" + port + "/vehicles/" + vehicleId, HttpMethod.DELETE, null, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
 */
}