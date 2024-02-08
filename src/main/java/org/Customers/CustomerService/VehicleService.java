package org.Customers.CustomerService;

import org.Customers.Model.Vehicle;

import java.util.List;

public interface VehicleService {
        Vehicle getVehicleByID(long vehicleId);
        void saveVehicle(Vehicle vehicle);
        void updateVehicle(Vehicle vehicle);
        void updateVehiclePrice(Vehicle vehicle, double price);
        void updateVehicleCosts(Vehicle vehicle, double costs);
        void deleteVehicle(long vehicleId);
        List<Vehicle> getAllVehicles();
 }

