package org.Rental.Controller;

import org.Rental.RentService.RentCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/rent")
public class RentController {

    @Autowired
    private RentCalculationService rentCalculationService;

    @PostMapping("/calculate")
    public double calculateTotalRent(@RequestParam char vehicle, @RequestParam String startDate, @RequestParam String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        return rentCalculationService.calculateTotalRent(vehicle, start, end);
    }

    @PostMapping("/save")
    public void saveRentDetails(@RequestParam char vehicle, @RequestParam String startDate, @RequestParam String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        rentCalculationService.saveRentDetails(vehicle, start, end);
    }
}
