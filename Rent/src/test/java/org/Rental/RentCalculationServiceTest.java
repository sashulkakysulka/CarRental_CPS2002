package org.Rental;

import org.Rental.RentService.RentCalculationService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
