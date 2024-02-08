package org.Customers.Repository;

import org.Customers.Model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

}

