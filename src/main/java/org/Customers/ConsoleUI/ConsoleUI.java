package org.Customers.ConsoleUI;

import org.Customers.Model.Customer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

@Component
public class ConsoleUI implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {
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
                        manageCustomers(scanner);
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
        }
    }
    private void manageCustomers(Scanner scanner) {
        while (true) {
            System.out.println("\nCustomer Management Menu");
            System.out.println("1. Add Customer");
            System.out.println("2. Update Customers");
            System.out.println("3. Partial Updates");
            System.out.println("4. Delete Customers");
            System.out.println("5. Retrieve Customers");
            System.out.println("0. Return to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addCustomer(scanner);
                    break;
                case 2:
                    updateCustomers(scanner);
                    break;
                case 3:
                    partialUpdateCustomers(scanner);
                    break;
                case 4:
                    deleteCustomer(scanner);
                    break;
                case 5:
                    retrieveCustomers(scanner);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
                    break;
            }
        }
    }

    private void addCustomer(Scanner scanner) {
        System.out.println("Adding a new customer...");

        System.out.println("Please enter customer details.");

        System.out.print("Full Name (at least 2 characters): ");
        String fullName = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Document Type: ");
        String documentType = scanner.nextLine();

        System.out.print("Document Number: ");
        String documentNumber = scanner.nextLine();

        System.out.print("Driver License Number: ");
        String driverLicenceNumber = scanner.nextLine();

        System.out.print("Years of Driving: ");
        Double yearsOfDriving = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Is Authorized (true/false): ");
        Boolean isAuthorized = scanner.nextBoolean();
        scanner.nextLine();

        Customer customer = new Customer();
        customer.setFullName(fullName);
        customer.setEmail(email);
        customer.setAge(age);
        customer.setDocumentType(documentType);
        customer.setDocumentNumber(documentNumber);
        customer.setDriverLicenceNumber(driverLicenceNumber);
        customer.setYearsOfDriving(yearsOfDriving);
        customer.setIsAuthorized(isAuthorized);

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/customers";

        HttpEntity<Customer> request = new HttpEntity<>(customer);
        Customer savedCustomer = restTemplate.postForObject(url, request, Customer.class);

        System.out.println("Customer created: " + savedCustomer.getId());

    }

    private void updateCustomers(Scanner scanner) {
        System.out.println("Updating an existing customer...");

    }

    private void partialUpdateCustomers(Scanner scanner) {
        System.out.println("Performing partial update on a customer...");

    }

    private void deleteCustomer(Scanner scanner) {
        System.out.println("Deleting a customer...");

    }

    private void retrieveCustomers(Scanner scanner) {
        System.out.println("Retrieving customer details...");

    }
}


