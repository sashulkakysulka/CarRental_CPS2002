package org.CarRental;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ICustomerRepository extends JpaRepository<CustomerEntity, Long> {
    @Modifying
    @Transactional
    @Query("update CustomerEntity c set c.fullName = :fullName where c.id = :id")
    void updateCustomerFullName(Long id, String fullName);

    @Modifying
    @Transactional
    @Query("update CustomerEntity c set c.email = :email where c.id = :id")
    void updateCustomerEmail(Long id, String email);

    @Modifying
    @Transactional
    @Query("update CustomerEntity c set c.age = :age where c.id = :id")
    void updateCustomerAge(Long id, int age);

    @Modifying
    @Transactional
    @Query("update CustomerEntity c set c.documentType = :documentType where c.id = :id")
    void updateCustomerDocumentType(Long id, String documentType);

    @Modifying
    @Transactional
    @Query("update CustomerEntity c set c.documentNumber = :documentNumber where c.id = :id")
    void updateCustomerDocumentNumber(Long id, String documentNumber);

    @Modifying
    @Transactional
    @Query("update CustomerEntity c set c.driverLicenceNumber = :driverLicenceNumber where c.id = :id")
    void updateCustomerDriverLicenceNumber(Long id, String driverLicenceNumber);

    @Modifying
    @Transactional
    @Query("update CustomerEntity c set c.yearsOfDriving = :yearsOfDriving where c.id = :id")
    void updateCustomerYearsOfDriving(Long id, Double yearsOfDriving);

    @Modifying
    @Transactional
    @Query("update CustomerEntity c set c.isAuthorized = :isAuthorized where c.id = :id")
    void updateCustomerIsAuthorized(Long id, Boolean isAuthorized);
}

