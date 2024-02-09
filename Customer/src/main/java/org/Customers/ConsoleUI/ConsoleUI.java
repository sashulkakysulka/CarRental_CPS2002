package org.Customers.ConsoleUI;

import org.Customers.Model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
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
                System.out.println("3. Rent a vehicle");
                System.out.println("4. Reports");
                System.out.println("0. Exit");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:

                        break;
                    case 2:
                        customerManagementService.manageCustomers(scanner);
                        break;
                    case 3:

                        break;
                    case 4:

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

