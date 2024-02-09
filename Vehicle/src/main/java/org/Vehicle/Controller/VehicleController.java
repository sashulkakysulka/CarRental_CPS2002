package org.Vehicle.Controller;

import org.Vehicle.VehicleService.*;
import org.Vehicle.Model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {
    private final VehicleService vehicleservice;

    @Autowired
    public VehicleController(VehicleService vehicleservice) {
        this.vehicleservice = vehicleservice;
    }

    @GetMapping("/vehicles/{vehicleId}")
    public Vehicle getVehicleByID(long vehicleId){
        return vehicleservice.getVehicleByID(vehicleId);
    }

    @GetMapping("/vehicles/all")
    public List<Vehicle> getAllVehicles(){
        return vehicleservice.getAllVehicles();
    }
    @PostMapping("vehicles/new")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle vehicle) {
        vehicleservice.saveVehicle(vehicle);
        return ResponseEntity.ok(vehicle);
    }

    @PutMapping("/vehicles/update/{vehicleId}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable Long vehicleId, @RequestBody Vehicle vehicle) {
        Vehicle existingVehicle = vehicleservice.getVehicleByID(vehicleId);
        if (existingVehicle != null) {
            vehicle.setVehicleId(vehicleId);
            vehicleservice.saveVehicle(vehicle);
        }
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/vehicles/{vehicleId}/price")
    public ResponseEntity<Vehicle> updateVehiclePrice(@PathVariable Long vehicleId, @RequestBody double price) {
        Vehicle existingVehicle = vehicleservice.getVehicleByID(vehicleId);
        if (existingVehicle != null) {
            existingVehicle.setVehicleId(vehicleId);
            vehicleservice.updateVehiclePrice(existingVehicle, price);
        }
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/vehicles/{vehicleId}/costs")
    public ResponseEntity<Vehicle> updateVehicleCosts(@PathVariable Long vehicleId, @RequestBody double costs) {
        Vehicle existingVehicle = vehicleservice.getVehicleByID(vehicleId);
        if (existingVehicle != null) {
            existingVehicle.setVehicleId(vehicleId);
            vehicleservice.updateVehicleCosts(existingVehicle, costs);
        }
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/vehicles/{vehicleId}/model")
    public ResponseEntity<Vehicle> updateVehicleModel(@PathVariable Long vehicleId, @RequestBody String model) {
        Vehicle existingVehicle = vehicleservice.getVehicleByID(vehicleId);
        if (existingVehicle != null) {
            existingVehicle.setVehicleId(vehicleId);
            vehicleservice.updateVehicleModel(existingVehicle, model);
        }
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/vehicles/{vehicleId}/type")
    public ResponseEntity<Vehicle> updateVehicleType(@PathVariable Long vehicleId, @RequestBody String type) {
        Vehicle existingVehicle = vehicleservice.getVehicleByID(vehicleId);
        if (existingVehicle != null) {
            existingVehicle.setVehicleId(vehicleId);
            vehicleservice.updateVehicleType(existingVehicle, type);
        }
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/vehicles/{vehicleId}/delete")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long vehicleId) {

        vehicleservice.deleteVehicle(vehicleId);
        return ResponseEntity.ok().build();
    }
}
