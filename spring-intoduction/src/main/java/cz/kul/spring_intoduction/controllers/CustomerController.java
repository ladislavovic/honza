package cz.kul.spring_intoduction.controllers;

import java.util.List;

import cz.kul.spring_intoduction.services.CustomerService;
import cz.kul.spring_intoduction.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

  @Autowired
  private CustomerService customerService;

  // Save operation 
  @PostMapping("/customers")
  public Customer saveCustomer(@RequestBody Customer customer) {
    System.out.println("Real 'CustomerService' class: " + customerService.getClass().getName());
    return customerService.createCustomer(customer);
  }

  // Read operation 
  @GetMapping("/customers")
  public List<Customer> fetchCustomerList() {
    return customerService.fetchCustomerList();
  }

  @GetMapping("/customers/{id}")
  public Customer readCustomerById(@PathVariable("id") Long customerId) {
    return customerService.fetchCustomer(customerId);
  }

  // Update operation 
  @PutMapping("/customers/{id}")
  public Customer updateCustomer(@RequestBody Customer customer, @PathVariable("id") Long customerId) {
    return customerService.updateCustomer(customerId, customer);
  }

  // Delete operation 
  @DeleteMapping("/customers/{id}")
  public String deleteCustomerById(@PathVariable("id") Long customerId) {
    customerService.deleteCustomerById(customerId);
    return "Deleted Successfully";
  }

}
