package org.Rental;

import org.Rental.RentService.RentCalculationService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RentCalculationServiceTest {
    @Test
    void testCalculateTotalRent() {
        RentCalculationService rentCalculationService = new RentCalculationService();

        double price = 5;
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(3);

        double totalRent = rentCalculationService.calculateTotalRent(price, startDate, endDate);

        assertEquals(15, totalRent);
    }
}
