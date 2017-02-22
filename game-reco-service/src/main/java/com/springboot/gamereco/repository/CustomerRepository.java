package com.springboot.gamereco.repository;

import com.springboot.gamereco.domain.Customer;
import org.springframework.data.repository.Repository;

/**
 * Customer Repository to perform CRUD on Customer resource.
 */
@org.springframework.stereotype.Repository
public interface CustomerRepository extends Repository<Customer, Long> {

    // Returns the customer by customerNumber;
    Customer findOne(Long id);

    // Stores customer
    Customer save(Customer customer);

    // Checks if the customer exists with customerNumber.
    boolean exists(long customerNumber);

    // Deletes customer by customerNumber
    void delete(long customerNumber);
}
