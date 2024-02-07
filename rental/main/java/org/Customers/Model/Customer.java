package org.Customers.Model;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @NotNull
    private Double yearsOfDriving;

    private Boolean isAuthorized;

    public void setId(Long id) { this.id = id; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setEmail(String email) { this.email = email; }
    public void setAge(int age) { this.age = age; }
    public void setDocumentType(String documentType) { this.documentType = documentType; }
    public void setDocumentNumber(String documentNumber) { this.documentNumber = documentNumber; }
    public void setDriverLicenceNumber(String driverLicenceNumber) { this.driverLicenceNumber = driverLicenceNumber; }
    public void setYearsOfDriving(Double yearsOfDriving) { this.yearsOfDriving = yearsOfDriving; }
    public void setIsAuthorized(Boolean isAuthorized) { this.isAuthorized = isAuthorized; }

    public static class Builder {
        private Long id;
        private String fullName;
        private String email;
        private int age;
        private String documentType;
        private String documentNumber;
        private String driverLicenceNumber;
        private Double yearsOfDriving;
        private Boolean isAuthorized;

        public Builder withId(Long id) { this.id = id; return this; }
        public Builder withFullName(String fullName) { this.fullName = fullName; return this; }
        public Builder withEmail(String email) { this.email = email; return this; }
        public Builder withAge(int age) { this.age = age; return this; }
        public Builder withDocumentType(String documentType) { this.documentType = documentType; return this; }
        public Builder withDocumentNumber(String documentNumber) { this.documentNumber = documentNumber; return this; }
        public Builder withDriverLicenceNumber(String driverLicenceNumber) { this.driverLicenceNumber = driverLicenceNumber; return this; }
        public Builder withYearsOfDriving(Double yearsOfDriving) { this.yearsOfDriving = yearsOfDriving; return this; }
        public Builder withIsAuthorized(Boolean isAuthorized) { this.isAuthorized = isAuthorized; return this; }

        public Customer build() {
            Customer customerEntity = new Customer();
            customerEntity.setId(id);
            customerEntity.setFullName(fullName);
            customerEntity.setEmail(email);
            customerEntity.setAge(age);
            customerEntity.setDocumentType(documentType);
            customerEntity.setDocumentNumber(documentNumber);
            customerEntity.setDriverLicenceNumber(driverLicenceNumber);
            customerEntity.setYearsOfDriving(yearsOfDriving);
            customerEntity.setIsAuthorized(isAuthorized);
            return customerEntity;
        }
    }
}

