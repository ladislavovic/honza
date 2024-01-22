package cz.kul.spring_intoduction.services;

import cz.kul.spring_intoduction.dao.CustomerRepository;
import cz.kul.spring_intoduction.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

  // NOTE: dependency injection. Here spring inject CustomerRepository.
  @Autowired
  private CustomerRepository customerRepository;

  @Override
  @Transactional
  public Customer createCustomer(Customer customer) {
    return customerRepository.save(customer);
  }

  @Override
  public List<Customer> fetchCustomerList() {
    return customerRepository.findAll();
  }

  @Override
  public Customer fetchCustomer(Long customerId) {
    return customerRepository.findById(customerId).get();
  }

  @Override
  @Transactional
  public Customer updateCustomer(Long customerId, Customer customer) {
    Customer customerDb = customerRepository.findById(customerId).get();

    if (StringUtils.hasText(customer.getFirstName())) {
      customerDb.setFirstName(customer.getFirstName());
    }

    if (StringUtils.hasText(customer.getLastName())) {
      customerDb.setLastName(customer.getLastName());
    }

    return customerDb;
  }

  @Override
  public void deleteCustomerById(Long customerId) {
    customerRepository.deleteById(customerId);
  }

}
