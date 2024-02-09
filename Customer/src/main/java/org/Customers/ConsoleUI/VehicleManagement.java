package org.Customers.ConsoleUI;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.Vehicle.Model.Vehicle;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

public class VehicleManagement {

    public void manageVehicles( Scanner scanner) {
        while (true) {
            System.out.println("\nVehicle Management Menu");
            System.out.println("1. Add Vehicle");
            System.out.println("2. Update Vehicle");
            System.out.println("3. Partial Updates");
            System.out.println("4. Delete Vehicles");
            System.out.println("5. Retrieve Vehicles");
            System.out.println("0. Return to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addVehicle(scanner);
                    break;
                case 2:
                    updateVehicle(scanner);
                    break;
                case 3:
                    partialVehicleUpdate(scanner);
                    break;
                case 4:
                    deleteVehicle();
                    break;
                case 5:
                    getAllVehicles();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
                    break;
            }
        }
    }
    private void addVehicle(Scanner scanner) {
        System.out.println("Adding a new vehicle");

        System.out.println("Enter Vehicle Model");
        String model = scanner.nextLine();

        System.out.println("Enter Type: ");
        String type = scanner.nextLine();

        System.out.println("Enter Cost: ");
        double cost = scanner.nextDouble();
        scanner.nextDouble();

        System.out.println("Enter Price per Day: ");
        double price = scanner.nextDouble();

        Vehicle newVehicle = new Vehicle();

        newVehicle.setModel(model);
        newVehicle.setType(type);
        newVehicle.setPricePerDay(price);
        newVehicle.setCostsPerDay(cost);

        RestTemplate restTemplate = RestTemplateSingleton.getInstance();
        String url = "http://localhost:8080/vehicles/new";

        HttpEntity<Vehicle> request = new HttpEntity<>(newVehicle);
        Vehicle savedVehicle = restTemplate.postForObject(url, request, Vehicle.class);

        assert savedVehicle != null;
        System.out.println("Customer created: " + savedVehicle.getVehicleId());

    }

    private void updateVehicle(Scanner scanner) {
            System.out.println("Updating an existing vehicle");
            System.out.println("Enter the ID of the vehicle to update:");
            long id = scanner.nextLong();
            scanner.nextLine();

            System.out.println("Enter Vehicle Model");
            String model = scanner.nextLine();

            System.out.println("Enter Type: ");
            String type = scanner.nextLine();

            System.out.println("Enter Cost: ");
            double cost = scanner.nextDouble();
            scanner.nextDouble();

            System.out.println("Enter Price per Day: ");
            double price = scanner.nextDouble();

            Vehicle newVehicle = new Vehicle(model, type, cost, price);

            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");
            HttpEntity<Vehicle> entity = new HttpEntity<>(newVehicle, headers);

            String url = "http://localhost:8080/vehicles/update" + id;
            RestTemplate restTemplate = RestTemplateSingleton.getInstance();
            try {
                ResponseEntity<Vehicle> response = restTemplate.exchange(url, HttpMethod.PUT, entity, Vehicle.class);
                System.out.println("Vehicle updated successfully: " + response.getBody());
            } catch (Exception e) {
                System.out.println("Failed to update vehicle. Error: " + e.getMessage());
            }
        }


        private void partialVehicleUpdate(Scanner scanner) {
            System.out.println("Performing partial update on a vehicle");
            System.out.println("Enter the ID of the vehicle to partially update:");
            long id = scanner.nextLong();
            scanner.nextLine();

            System.out.println("Select the attribute to update:");
            System.out.println("1. Cost");
            System.out.println("2. Price per Day");
            System.out.println("3. Type");
            System.out.println("4. Model");
            System.out.println("0. Go back");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter cost");
                    double cost = scanner.nextDouble();
                    updateVehicleCost(cost, id);
                    break;
                case 2:
                    System.out.println("Enter price");
                    double price = scanner.nextDouble();
                    updateVehiclePrice(price , id);
                    break;
                case 3:
                    System.out.println("Enter type");
                    String type = scanner.nextLine();
                    updateVehicleType(type, id);
                    break;
                case 4:
                    System.out.println("Enter model");
                    String model = scanner.nextLine();
                    updateVehicleModel(model, id);
                case 0:
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
                    break;
            }
        }

        public void updateVehicleCost(double attribute, long id){
            String url = "http://localhost:8080/vehicles/" + id + "/" + "costs";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);


            ObjectMapper objectMapper = new ObjectMapper();
            String jsonValue = objectMapper.convertValue(attribute, String.class);


            HttpEntity<String> entity = new HttpEntity<>(jsonValue, headers);
            RestTemplate restTemplate = RestTemplateSingleton.getInstance();

            try {
                restTemplate.exchange(url, HttpMethod.PATCH, entity, String.class);
                System.out.println("Vehicle cost updated successfully.");
            } catch (Exception e) {
                System.out.println("Failed to update vehicle cost. Error: " + e.getMessage());
            }
        }

    public void updateVehiclePrice(double attribute, long id){
        String url = "http://localhost:8080/vehicles/" + id + "/" + "price";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);


        ObjectMapper objectMapper = new ObjectMapper();
        String jsonValue = objectMapper.convertValue(attribute, String.class);


        HttpEntity<String> entity = new HttpEntity<>(jsonValue, headers);
        RestTemplate restTemplate = RestTemplateSingleton.getInstance();

        try {
            restTemplate.exchange(url, HttpMethod.PATCH, entity, String.class);
            System.out.println("Vehicle price updated successfully.");
        } catch (Exception e) {
            System.out.println("Failed to update vehicle price. Error: " + e.getMessage());
        }
    }
    public void updateVehicleModel(String attribute, long id){
        String url = "http://localhost:8080/vehicles/" + id + "/" + "model";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);


        ObjectMapper objectMapper = new ObjectMapper();
        String jsonValue = objectMapper.convertValue(attribute, String.class);


        HttpEntity<String> entity = new HttpEntity<>(jsonValue, headers);
        RestTemplate restTemplate = RestTemplateSingleton.getInstance();

        try {
            restTemplate.exchange(url, HttpMethod.PATCH, entity, String.class);
            System.out.println("Vehicle model updated successfully.");
        } catch (Exception e) {
            System.out.println("Failed to update vehicle model. Error: " + e.getMessage());
        }
    }

    public void updateVehicleType(String attribute, long id){
        String url = "http://localhost:8080/vehicles/" + id + "/" + "type";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);


        ObjectMapper objectMapper = new ObjectMapper();
        String jsonValue = objectMapper.convertValue(attribute, String.class);


        HttpEntity<String> entity = new HttpEntity<>(jsonValue, headers);
        RestTemplate restTemplate = RestTemplateSingleton.getInstance();

        try {
            restTemplate.exchange(url, HttpMethod.PATCH, entity, String.class);
            System.out.println("Vehicle type updated successfully.");
        } catch (Exception e) {
            System.out.println("Failed to update vehicle type. Error: " + e.getMessage());
        }
    }


    private void deleteVehicle() {
            System.out.println("Deleting a vehicle");
        //TODO
        }

        private void getAllVehicles() {
            System.out.println("Retrieving vehicle details");
        //TODO
        }
    }

