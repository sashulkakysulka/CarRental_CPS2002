package org.Vehicle.Decorators;

import org.Vehicle.Model.Vehicle;
import org.Vehicle.Model.VehicleDecorator;

public class AddDiscountDecorator implements VehicleDecorator {
    @Override
    public void decorateVehicle(Vehicle vehicle) {
        double cost = vehicle.getCostsPerDay();
        double discount = cost*0.15;
        vehicle.setCostsPerDay(cost - discount);
    }
}
