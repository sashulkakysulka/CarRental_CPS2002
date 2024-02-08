
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.Vehicle.Model.*;
import org.Vehicle.VehicleService.VehicleService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Nested
@SpringBootTest
class VehicleControllerTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private VehicleService vehicleMockService;

    @Test
    void contextLoads() {
    }


    @Test
    public void testCreateVehicle() {
        List<DateRange> listOfDates = new ArrayList<>();
        DateRange range = new DateRange(LocalDate.of(2024,1,1), LocalDate.of(2024,1,5));
        listOfDates.add(range);
        Vehicle vehicle = new Vehicle("type", "model", 24.50,listOfDates, 12.50 );

        ResponseEntity<Vehicle> expectedVehicle = new ResponseEntity<>(vehicle, HttpStatus.CREATED);
        String endpoint = "/vehicles";


        ResponseEntity<ResponseEntity> responseEntity = testRestTemplate.postForEntity(endpoint, vehicle, ResponseEntity.class);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }
}