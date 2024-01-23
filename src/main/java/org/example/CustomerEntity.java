package org.example;

import lombok.Data;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;
import java.util.ArrayList;


@Entity
@Data
public class CustomerEntity {
    @Getter
    private String fullName;
    @Getter
    private String email;
    @Getter
    private int age;
    @Getter
    private String documentType;
    @Getter
    private String documentNumber;
    @Getter
    private String driverLicenceNumber;
    @Getter
    private List<String> rentList = new ArrayList<>();
    @Getter
    @GeneratedValue
    @Id
    private Long id;


    public CustomerEntity() {
    }
    public CustomerEntity(String fullName, int age, String driverLicenceNumber) {
        this.fullName = fullName;
        this.age = age;
        this.driverLicenceNumber = driverLicenceNumber;
    }
    public CustomerEntity(String fullName,int age, String email,
                          String documentNumber, String documentType, String driverLicenceNumber, List<String> rentList,
                          Long id) {
        this.fullName = fullName;
        this.age = age;
        this.email = email;
        this.documentNumber = documentNumber;
        this.documentType = documentType;
        this.driverLicenceNumber = driverLicenceNumber;
        this.rentList = rentList;
        this.id = id;
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
}

