package edu.um.cps2002.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {
    private final VehicleService vehicleservice;

    @Autowired
    public VehicleController(VehicleService vehicleservice) {
        this.vehicleservice = vehicleservice;
    }

    @GetMapping("/{vehicleId}")
    public Vehicle getVehicleByID(long vehicleId){
        return vehicleservice.getVehicleByID(vehicleId);
    }

    @GetMapping
    public List<Vehicle> getAllVehicles(){
        return vehicleservice.getAllVehicles();
    }
    @PostMapping
    public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle vehicle) {
        vehicleservice.saveVehicle(vehicle);
        return ResponseEntity.ok(vehicle);
    }

    @PutMapping("/{vehicleId}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable Long vehicleId, @RequestBody Vehicle vehicle) {
        Vehicle existingVehicle = vehicleservice.getVehicleByID(vehicleId);
        if (existingVehicle != null) {
            vehicle.setVehicleId(vehicleId);
            vehicleservice.saveVehicle(vehicle);
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long vehicleId) {

        vehicleservice.deleteVehicle(vehicleId);
        return ResponseEntity.ok().build();
    }
}
