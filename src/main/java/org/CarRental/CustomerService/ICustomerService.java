package org.CarRental.CustomerService;

import java.util.List;
import java.util.Optional;
import org.CarRental.Model.Customer;

public interface ICustomerService {
    Customer createCustomer(Customer customer);
    List<Customer> getAllCustomers();
    Optional<Customer> getCustomerById(Long id);
    Customer updateCustomer(Long id, Customer customerDetails);
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

