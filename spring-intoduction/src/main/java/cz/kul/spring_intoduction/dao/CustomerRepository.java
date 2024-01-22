package cz.kul.spring_intoduction.dao;

import cz.kul.spring_intoduction.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
