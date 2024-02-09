package org.Customers.ConsoleUI;

import org.Customers.Model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import java.util.Scanner;

@Component
public class ConsoleUI implements CommandLineRunner {
    private final Scanner scanner = new Scanner(System.in);
    @Autowired
    private CustomerManagementService customerManagementService;
    @Override
    public void run(String... args) throws Exception {
        try {
            while (true) {
                System.out.println("Car Rental Application");
                System.out.println("Choose an option:");
                System.out.println("1. Manage vehicles");
                System.out.println("2. Manage users");
                System.out.println("3. Rent");
                System.out.println("0. Exit");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        VehicleManagement vehiclemanager = new VehicleManagement();
                        vehiclemanager.manageVehicles(scanner);
                        break;
                    case 2:
                        customerManagementService.manageCustomers(scanner);
                        break;
                    case 3:
                        RentalManagement rentman = new RentalManagement();
                        rentman.rentCost(scanner);
                        break;
                    case 0:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid option, please try again.");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
