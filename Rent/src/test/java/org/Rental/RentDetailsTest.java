package org.Rental;

import org.Rental.Model.RentDetails;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class RentDetailsTest {
    @Test
    void testRentDetailsConstructor() {
        RentDetails rentDetails = new RentDetails('1', LocalDate.now(), LocalDate.now().plusDays(3));

        assertEquals('1', rentDetails.getVehicle());
        assertNotNull(rentDetails.getStartDate());
        assertNotNull(rentDetails.getEndDate());
        assertNull(rentDetails.getId());
    }
}
