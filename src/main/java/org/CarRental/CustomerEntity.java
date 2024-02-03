package org.CarRental;

import lombok.Data;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;
import java.util.ArrayList;
import javax.validation.constraints.*;

@Getter
@Entity
@Data
public class CustomerEntity {
    @NotNull(message = "Full name cannot be null")
    @Size(min = 2, message = "Full name must be at least 2 characters long")
    private String fullName;

    @Email(message = "Email should be valid")
    private String email;

    private int age;

    @NotNull
    private String documentType;

    @NotNull
    private String documentNumber;

    @NotNull
    private String driverLicenceNumber;

    private List<String> rentList = new ArrayList<>();

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotNull
    private Double yearsOfDriving;

    private Boolean isAuthorized;

    public CustomerEntity() {
    }
    public CustomerEntity(String fullName, int age, String driverLicenceNumber, Double yearsOfDriving) {
        this.fullName = fullName;
        this.age = age;
        this.driverLicenceNumber = driverLicenceNumber;
        this.yearsOfDriving = yearsOfDriving;
    }
    public CustomerEntity(String fullName,int age, String email,
                          String documentNumber, String documentType, String driverLicenceNumber, List<String> rentList,
                          Double yearsOfDriving) {
        this.fullName = fullName;
        this.age = age;
        this.email = email;
        this.documentNumber = documentNumber;
        this.documentType = documentType;
        this.driverLicenceNumber = driverLicenceNumber;
        this.rentList = rentList;
        this.yearsOfDriving = yearsOfDriving;
    }
    public CustomerEntity(String fullName,int age, String email,
                          String documentNumber, String documentType, String driverLicenceNumber, List<String> rentList,
                          Long id, Double yearsOfDriving, Boolean isAuthorized) {
        this.fullName = fullName;
        this.age = age;
        this.email = email;
        this.documentNumber = documentNumber;
        this.documentType = documentType;
        this.driverLicenceNumber = driverLicenceNumber;
        this.rentList = rentList;
        this.id = id;
        this.yearsOfDriving = yearsOfDriving;
        this.isAuthorized = isAuthorized;
    }
    public void setId(Long id){
        this.id = id;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }
    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }
    public void setDriverLicenceNumber(String driverLicenceNumber) {
        this.driverLicenceNumber = driverLicenceNumber;
    }
    public void setRentList(List<String> rentList) {
        this.rentList = rentList;
    }
    public void setYearsOfDriving(Double yearsOfDriving) {this.yearsOfDriving = yearsOfDriving;}
    public void setIsAuthorized(Boolean isAuthorized) {this.isAuthorized=isAuthorized;}
}

