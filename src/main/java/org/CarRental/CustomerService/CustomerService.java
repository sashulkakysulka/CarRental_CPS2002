package org.CarRental.CustomerService;

import org.CarRental.CustomerNotFoundException;
import org.CarRental.Model.CustomerEntity;
import org.CarRental.Repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {

    private final ICustomerRepository customerRepository;

    @Autowired
    public CustomerService(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerEntity createCustomer(CustomerEntity customer) {

        if (customer.getAge() > 18 && customer.getYearsOfDriving() > 1) {
            customer.setIsAuthorized(true);
        } else {
            customer.setIsAuthorized(false);
        }

        return customerRepository.save(customer);
    }

    @Override
    public List<CustomerEntity> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<CustomerEntity> getCustomerById(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new CustomerNotFoundException("No customer found with ID: " + id);
        }
        return customerRepository.findById(id);
    }

    @Override
    public CustomerEntity updateCustomer(Long id, CustomerEntity customerDetails) {
        CustomerEntity customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found for this id :: " + id));
        customer.setFullName(customerDetails.getFullName());
        customer.setAge(customerDetails.getAge());
        customer.setEmail(customerDetails.getEmail());
        customer.setDocumentNumber(customerDetails.getDocumentNumber());
        customer.setDocumentType(customerDetails.getDocumentType());
        customer.setDriverLicenceNumber(customerDetails.getDriverLicenceNumber());
        customer.setYearsOfDriving(customerDetails.getYearsOfDriving());
        return customerRepository.save(customer);
    }
    public void updateCustomerFullName(Long id, String fullName) {
        if (!customerRepository.existsById(id)) {
            throw new CustomerNotFoundException("No customer found with ID: " + id);
        }
        customerRepository.updateCustomerFullName(id, fullName);
    }

    public void updateCustomerEmail(Long id, String email) {
        if (!customerRepository.existsById(id)) {
            throw new CustomerNotFoundException("No customer found with ID: " + id);
        }
        customerRepository.updateCustomerEmail(id, email);
    }

    public void updateCustomerAge(Long id, int age) {
        if (!customerRepository.existsById(id)) {
            throw new CustomerNotFoundException("No customer found with ID: " + id);
        }
        customerRepository.updateCustomerAge(id, age);
    }

    public void updateCustomerDocumentType(Long id, String documentType) {
        if (!customerRepository.existsById(id)) {
            throw new CustomerNotFoundException("No customer found with ID: " + id);
        }
        customerRepository.updateCustomerDocumentType(id, documentType);
    }

    public void updateCustomerDocumentNumber(Long id, String documentNumber) {
        if (!customerRepository.existsById(id)) {
            throw new CustomerNotFoundException("No customer found with ID: " + id);
        }
        customerRepository.updateCustomerDocumentNumber(id, documentNumber);
    }

    public void updateCustomerDriverLicenceNumber(Long id, String driverLicenceNumber) {
        if (!customerRepository.existsById(id)) {
            throw new CustomerNotFoundException("No customer found with ID: " + id);
        }
        customerRepository.updateCustomerDriverLicenceNumber(id, driverLicenceNumber);
    }

    public void updateCustomerYearsOfDriving(Long id, Double yearsOfDriving) {
        if (!customerRepository.existsById(id)) {
            throw new CustomerNotFoundException("No customer found with ID: " + id);
        }
        customerRepository.updateCustomerYearsOfDriving(id, yearsOfDriving);
    }

    public void updateCustomerIsAuthorized(Long id, Boolean isAuthorized) {
        if (!customerRepository.existsById(id)) {
            throw new CustomerNotFoundException("No customer found with ID: " + id);
        }
        customerRepository.updateCustomerIsAuthorized(id, isAuthorized);
    }

    @Override
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new CustomerNotFoundException("No customer found with ID: " + id);
        }
        customerRepository.deleteById(id);
    }
}

