package org.CarRental.CustomerService;

import java.util.List;
import java.util.Optional;
import org.CarRental.Model.CustomerEntity;

public interface ICustomerService {
    CustomerEntity createCustomer(CustomerEntity customer);
    List<CustomerEntity> getAllCustomers();
    Optional<CustomerEntity> getCustomerById(Long id);
    CustomerEntity updateCustomer(Long id, CustomerEntity customerDetails);
    public void updateCustomerFullName(Long id, String fullName);
    public void updateCustomerEmail(Long id, String email);
    public void updateCustomerAge(Long id, int age);
    public void updateCustomerDocumentType(Long id, String documentType);
    public void updateCustomerDocumentNumber(Long id, String documentNumber);
    public void updateCustomerDriverLicenceNumber(Long id, String driverLicenceNumber);
    public void updateCustomerYearsOfDriving(Long id, Double yearsOfDriving);
    public void updateCustomerIsAuthorized(Long id, Boolean isAuthorized);
    void deleteCustomer(Long id);
}
