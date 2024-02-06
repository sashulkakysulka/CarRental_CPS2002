package org.CarRental.Controller;

import org.CarRental.CustomerService.CustomerService;
import org.CarRental.CustomerService.ICustomerService;
import org.CarRental.Model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.mappers.ModelMapper;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    ICustomerService customerService;

    @Autowired
    ModelMapper modelMapper;


    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
        Customer savedCustomer = customerService.createCustomer(customer);
        return ResponseEntity.ok(savedCustomer);
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Optional<Customer> customer = customerService.getCustomerById(id);
        return customer.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetails) {
        Customer updatedCustomer = customerService.updateCustomer(id, customerDetails);
        return ResponseEntity.ok(updatedCustomer);
    }

    @PatchMapping("/{id}/fullName")
    public ResponseEntity<?> updateCustomerFullName(@PathVariable Long id, @RequestBody String fullName) {
        customerService.updateCustomerFullName(id, fullName);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/email")
    public ResponseEntity<?> updateCustomerEmail(@PathVariable Long id, @RequestBody String email) {
        customerService.updateCustomerEmail(id, email);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/age")
    public ResponseEntity<?> updateCustomerAge(@PathVariable Long id, @RequestBody int age) {
        customerService.updateCustomerAge(id, age);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/documentType")
    public ResponseEntity<?> updateCustomerDocumentType(@PathVariable Long id, @RequestBody String documentType) {
        customerService.updateCustomerDocumentType(id, documentType);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/documentNumber")
    public ResponseEntity<?> updateCustomerDocumentNumber(@PathVariable Long id, @RequestBody String documentNumber) {
        customerService.updateCustomerDocumentNumber(id, documentNumber);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/driverLicenceNumber")
    public ResponseEntity<?> updateCustomerDriverLicenceNumber(@PathVariable Long id, @RequestBody String driverLicenceNumber) {
        customerService.updateCustomerDriverLicenceNumber(id, driverLicenceNumber);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/yearsOfDriving")
    public ResponseEntity<?> updateCustomerYearsOfDriving(@PathVariable Long id, @RequestBody Double yearsOfDriving) {
        customerService.updateCustomerYearsOfDriving(id, yearsOfDriving);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/isAuthorized")
    public ResponseEntity<?> updateCustomerIsAuthorized(@PathVariable Long id, @RequestBody Boolean isAuthorized) {
        customerService.updateCustomerIsAuthorized(id, isAuthorized);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }
}

