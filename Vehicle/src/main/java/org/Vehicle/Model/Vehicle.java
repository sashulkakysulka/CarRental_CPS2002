package org.Vehicle.Model;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.List;
import javax.persistence.*;

@Entity
public class Vehicle{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long vehicleId;

    private String type;
    private String model;
    private double pricePerDay;
    private double costsPerDay;
@Transient
    private List<DateRange> datesRented;

    public Vehicle(){} //Empty Constructor

    public Vehicle(String type, String model, double price, double costsPerDay){
        this.type = type;
        this.model = model;
        this.pricePerDay = price;
        this.costsPerDay = costsPerDay;
    }

    public long getVehicleId() {
        return vehicleId;
    }
    public void setVehicleId(long vehicleId){
        this.vehicleId = vehicleId;
    }

    public String getType(){
        return type;
    }
    public void setType(String type){

        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPricePerDay(){

        return pricePerDay;
    }
    public void setPricePerDay(double pricePerDay) {

        this.pricePerDay = pricePerDay;
    }

    public void setDatesRented(List<DateRange> datesRented) {
        this.datesRented = datesRented;
    }
    public List<DateRange> getDatesRented(){
        return datesRented;
    }
    public double getCostsPerDay(){

        return costsPerDay;
    }

    public void setCostsPerDay(double costsPerDay){

        this.costsPerDay = costsPerDay;
    }

    public double calculateProfits() {
        double loss = getCostsPerDay() * 365;
        double profits = 0;
        for(int i = 0; i < datesRented.size(); i++){
            LocalDate startDate = datesRented.get(i).getStartDate();
            LocalDate endDate = datesRented.get(i).getEndDate();

            profits += ChronoUnit.DAYS.between(startDate, endDate);
        }
      return profits - loss;
    }

    public boolean isAvailable(LocalDate startDate, LocalDate endDate){
        boolean isAvailable = true;

        for(int i = 0;i < datesRented.size() ; i++){
            LocalDate rentedStartDate = datesRented.get(i).getStartDate();
            LocalDate rentedEndDate = datesRented.get(i).getEndDate();

            if((startDate.isBefore(rentedEndDate) || startDate.isEqual(rentedEndDate)) && (startDate.isAfter(rentedStartDate) || startDate.isEqual(rentedStartDate))){
                isAvailable = false;
            }
            else if((endDate.isBefore(rentedEndDate) || endDate.isEqual(rentedEndDate)) && (endDate.isAfter(rentedStartDate) || endDate.isEqual(rentedStartDate))){
                isAvailable = false;
            }

        }
        
        return isAvailable;
    }
}