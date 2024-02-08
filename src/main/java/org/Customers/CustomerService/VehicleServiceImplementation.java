package org.Customers.CustomerService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

import org.Customers.Controller.VehicleController;
import org.Customers.Repository.VehicleRepository;
import org.Customers.Model.Vehicle;
import org.Customers.VehicleApplication;
import org.Customers.VehicleNotFound;

@Service
public class VehicleServiceImplementation implements VehicleService {

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleServiceImplementation(VehicleRepository vehicleRepository){
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Vehicle getVehicleByID(long vehicleId){
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(vehicleId);
        return optionalVehicle.orElseThrow(()-> new VehicleNotFound("Vehicle not found"));
    }
    @Override
    public void saveVehicle(Vehicle vehicle){
        vehicleRepository.save(vehicle);
    }

    @Override
    public void updateVehicle(Vehicle vehicle){
        vehicleRepository.save(vehicle);
    }

    @Override
    public void updateVehiclePrice(Vehicle vehicle, double price){
        vehicle.setPricePerDay(price);
        vehicleRepository.save(vehicle);
    }

    @Override
    public void updateVehicleCosts(Vehicle vehicle, double costs){
        vehicle.setCostsPerDay(costs);
        vehicleRepository.save(vehicle);
    }
    @Override
    public void deleteVehicle(long vehicleId){
        vehicleRepository.deleteById(vehicleId);
    }

    public List<Vehicle>  getAllVehicles(){
        return vehicleRepository.findAll();
    }

}
