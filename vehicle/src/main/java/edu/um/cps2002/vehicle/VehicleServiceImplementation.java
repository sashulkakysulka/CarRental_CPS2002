package edu.um.cps2002.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

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
        return optionalVehicle.orElse(null);
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
    public void deleteVehicle(long vehicleId){
        vehicleRepository.deleteById(vehicleId);
    }

    public List<Vehicle>  getAllVehicles(){
        return vehicleRepository.findAll();
    }

}
