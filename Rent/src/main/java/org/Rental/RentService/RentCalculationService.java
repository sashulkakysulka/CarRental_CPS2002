package org.Rental.RentService;

import org.Rental.Model.RentDetails;
import org.Rental.Repository.RentDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class RentCalculationService {

    @Autowired
    private RentDetailsRepository rentDetailsRepository;

    public double calculateTotalRent(double price, LocalDate startDate, LocalDate endDate) {
        long totalNoDays = ChronoUnit.DAYS.between(startDate, endDate);
        return price * totalNoDays;
    }

    public void saveRentDetails(LocalDate startDate, LocalDate endDate) {
        RentDetails rentDetails = new RentDetails(startDate, endDate);
        rentDetailsRepository.save(rentDetails);
    }
}
