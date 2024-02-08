package org.Vehicle.Repository;

import org.Vehicle.Model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

}

