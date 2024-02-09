package org.Customers.ConsoleUI;

import org.Vehicle.Model.Vehicle;
import org.Rental.Model.RentDetails;
import org.Rental.Controller.RentController;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class RentalManagement {
    public void rentCost(Scanner scanner) {
        Vehicle vehicle = new Vehicle();
        RentDetails rent = new RentDetails();

        double price = vehicle.getPricePerDay();

        System.out.print("Enter the start date (YYYY-MM-DD): ");
        String inputStartDate = scanner.next();
        LocalDate startDate = LocalDate.parse(inputStartDate);
        rent.setStartDate(startDate);

        System.out.print("Enter the end date (YYYY-MM-DD): ");
        String inputEndDate = scanner.next();
        LocalDate endDate = LocalDate.parse(inputEndDate);
        rent.setEndDate(endDate);

        RentController calc = new RentController();

        double total = calc.calculateTotalRent(price, inputStartDate, inputEndDate);

        double diff = total - vehicle.getCostsPerDay();

        if(diff < 0) {
            System.out.println("There was a loss of " + (diff*-1));
        }
        else {
            System.out.println("There was a profit of " + diff);
        }
    }

}
