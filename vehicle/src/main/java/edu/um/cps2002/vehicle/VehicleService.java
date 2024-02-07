package edu.um.cps2002.vehicle;

import java.util.List;

public interface VehicleService {
        Vehicle getVehicleByID(long vehicleId);
        void saveVehicle(Vehicle vehicle);
        void updateVehicle(Vehicle vehicle);
        void deleteVehicle(long vehicleId);
        List<Vehicle> getAllVehicles();
 }

