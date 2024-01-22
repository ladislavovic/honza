package cz.kul.spring_intoduction.services;

import cz.kul.spring_intoduction.entities.Customer;

import java.util.List;

public interface CustomerService {

  // Create operation
  Customer createCustomer(Customer customer);

  Customer fetchCustomer(Long customerId);

  // Read operation 
  List<Customer> fetchCustomerList();

  // Update operation 
  Customer updateCustomer(Long customerId, Customer customer);

  // Delete operation 
  void deleteCustomerById(Long customerId);

}
