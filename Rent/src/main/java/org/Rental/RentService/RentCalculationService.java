package org.Rental.RentService;

import org.Rental.Model.RentDetails;
import org.Rental.Repository.RentDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class RentCalculationService {

    @Autowired
    private RentDetailsRepository rentDetailsRepository;

    public double calculateTotalRent(char vehicle, LocalDate startDate, LocalDate endDate) {
        double price = getVehiclePrice(vehicle);
        long totalNoDays = ChronoUnit.DAYS.between(startDate, endDate);
        return price * totalNoDays;
    }

    private double getVehiclePrice(char vehicle) {
        switch (vehicle) {
            case '1':
                return 5;
            case '2':
                return 10;
            case '3':
                return 20;
            case '4':
                return 35;
            case '5':
                return 40;
            case '6':
                return 56;
            case '7':
                return 30;
            case '8':
                return 50;
            case '9':
                return 100;
            default:
                throw new IllegalArgumentException("Invalid case!");
        }
    }

    public void saveRentDetails(char vehicle, LocalDate startDate, LocalDate endDate) {
        RentDetails rentDetails = new RentDetails(vehicle, startDate, endDate);
        rentDetailsRepository.save(rentDetails);
    }
}
