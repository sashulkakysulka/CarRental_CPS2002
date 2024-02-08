package um.edu.cps2002.RentSystem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

public class RentCalculationServiceTest {
    @Test
    void testCalculateTotalRent() {
        RentCalculationService rentCalculationService = new RentCalculationService();

        char vehicle = '1';
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(3);

        double totalRent = rentCalculationService.calculateTotalRent(vehicle, startDate, endDate);

        assertEquals(15, totalRent);
    }
}
