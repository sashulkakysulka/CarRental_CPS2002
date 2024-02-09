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
                System.out.println("3. Rent a vehicle");
                System.out.println("4. Reports");
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

    private void manageCustomers( Scanner scanner) {
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
        System.out.println("Adding a new customer");

        System.out.println("Please enter customer details.");

        System.out.print("Full Name (at least 2 characters): ");
        String fullName = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Age: ");
        Double age = scanner.nextDouble();
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

        RestTemplate restTemplate = RestTemplateSingleton.getInstance();
        String url = "http://localhost:8080/customers";

        HttpEntity<Customer> request = new HttpEntity<>(customer);
        Customer savedCustomer = restTemplate.postForObject(url, request, Customer.class);

        assert savedCustomer != null;
        System.out.println("Customer created: " + savedCustomer.getId());

    }

    private void updateCustomers(Scanner scanner) {
        System.out.println("Updating an existing customer");
        System.out.println("Enter the ID of the customer to update:");
        long id = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Enter updated full name:");
        String fullName = scanner.nextLine();

        System.out.println("Enter updated email:");
        String email = scanner.nextLine();

        System.out.println("Enter updated age:");
        Double age = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Enter updated document type:");
        String documentType = scanner.nextLine();

        System.out.println("Enter updated document number:");
        String documentNumber = scanner.nextLine();

        System.out.println("Enter updated driver licence number:");
        String driverLicenceNumber = scanner.nextLine();

        System.out.println("Enter updated years of driving:");
        Double yearsOfDriving = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Is the customer authorized? (true/false):");
        Boolean isAuthorized = scanner.nextBoolean();
        scanner.nextLine();

        Customer customerDetails = new Customer();
        customerDetails.setFullName(fullName);
        customerDetails.setEmail(email);
        customerDetails.setAge(age);
        customerDetails.setDocumentType(documentType);
        customerDetails.setDocumentNumber(documentNumber);
        customerDetails.setDriverLicenceNumber(driverLicenceNumber);
        customerDetails.setYearsOfDriving(yearsOfDriving);
        customerDetails.setIsAuthorized(isAuthorized);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<Customer> entity = new HttpEntity<>(customerDetails, headers);

        String url = "http://localhost:8080/customers/update" + id;
        RestTemplate restTemplate = RestTemplateSingleton.getInstance();
        try {
            ResponseEntity<Customer> response = restTemplate.exchange(url, HttpMethod.PUT, entity, Customer.class);
            System.out.println("Customer updated successfully: " + response.getBody());
        } catch (Exception e) {
            System.out.println("Failed to update customer. Error: " + e.getMessage());
        }
    }

    private void partialUpdateCustomers(Scanner scanner) {
        System.out.println("Performing partial update on a customer");
        System.out.println("Enter the ID of the customer to partially update:");
        Long id = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Select the attribute to update:");
        System.out.println("1. Full Name");
        System.out.println("2. Email");
        System.out.println("3. Age");
        System.out.println("4. Document Type");
        System.out.println("5. Document Number");
        System.out.println("6. Driver Licence Number");
        System.out.println("7. Years Of Driving");
        System.out.println("8. Is Authorized");
        System.out.println("0. Go back");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                updateAttribute("fullName", id);
                break;
            case 2:
                updateAttribute("email", id);
                break;
            case 3:
                updateAttribute("age", id);
                break;
            case 4:
                updateAttribute("documentType", id);
                break;
            case 5:
                updateAttribute("documentNumber", id);
                break;
            case 6:
                updateAttribute("driverLicenceNumber", id);
                break;
            case 7:
                updateAttribute("yearsOfDriving", id);
                break;
            case 8:
                updateAttribute("isAuthorized", id);
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid option, please try again.");
                break;
        }
    }
    private void updateAttribute(String attribute, Long id) {
        System.out.println("Enter the new value for " + attribute + ":");

        Object value;
        if ("age".equals(attribute) || "yearsOfDriving".equals(attribute)) {
            value = scanner.nextDouble();
            scanner.nextLine();
        } else if ("isAuthorized".equals(attribute)) {
            value = scanner.nextBoolean();
            scanner.nextLine();
        } else {
            value = scanner.nextLine();
        }

        String url = "http://localhost:8080/customers/" + id + "/" + attribute;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);

        String jsonValue = convertValueToJson(value, attribute);

        HttpEntity<String> entity = new HttpEntity<>(jsonValue, headers);
        RestTemplate restTemplate = RestTemplateSingleton.getInstance();
        try {
            restTemplate.exchange(url, HttpMethod.PATCH, entity, String.class);
            System.out.println("Customer " + attribute + " updated successfully.");
        } catch (Exception e) {
            System.out.println("Failed to update customer. Error: " + e.getMessage());
        }
    }

    private String convertValueToJson(Object value, String attribute) {
        if (value instanceof String) {
            return "\"" + value + "\"";
        } else {
            return value.toString();
        }
    }

    private void deleteCustomer(Scanner scanner) {
        System.out.println("Deleting a customer");

    }

    private void retrieveCustomers(Scanner scanner) {
        System.out.println("Retrieving customer details");

    }

}
