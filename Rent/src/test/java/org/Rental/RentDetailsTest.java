package org.Rental;

import org.Rental.Model.RentDetails;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class RentDetailsTest {
    @Test
    void testRentDetailsConstructor() {
        RentDetails rentDetails = new RentDetails(LocalDate.now(), LocalDate.now().plusDays(3));

        assertNotNull(rentDetails.getStartDate());
        assertNotNull(rentDetails.getEndDate());
        assertNull(rentDetails.getId());
    }
}
