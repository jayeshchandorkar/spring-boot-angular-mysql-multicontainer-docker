package com.springboot.gamereco.service;

import com.springboot.gamereco.exception.CustomerNotfoundException;
import com.springboot.gamereco.domain.Customer;
import com.springboot.gamereco.domain.Recommendation;
import com.springboot.gamereco.exception.InActiveRecommendationException;
import com.springboot.gamereco.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CustomerService class
 */
@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    /**
     * Add Recommendations provided for the customers.
     *
     * @param customers
     */
    public void addRecommendations(List<Customer> customers){
        if(customers != null && !customers.isEmpty()){
            for(Customer customer : customers){
                addRecommendationForCustomer(customer);
            }
        }
    }

    /**
     * Add recommendations for the single customer.
     *
     * @param customer
     */
    public void addRecommendationForCustomer(Customer customer){
        if(customer != null){
            // Checks if the customer exists.
            // For now just delete the existing customer alog with the recommendations.
            if(customerRepository.exists(customer.getCustomerNumber())){
                customerRepository.delete(customer.getCustomerNumber());
            }
            // Stores customer and its recommendations.
            customerRepository.save(customer);
        }
    }

    /**
     * Returns the list of recommendations for the customer containing
     * maximum {count} number of recommendations.
     *
     * @param customerNumber
     * @param count
     * @return
     * @throws Exception
     */
    public List<Recommendation> getRecommendations(long customerNumber, int count)throws Exception{
        Customer customer = customerRepository.findOne(customerNumber);
        // If customer not found throws CustomerNotfoundException.
        if(customer == null){
            throw new CustomerNotfoundException();
        }else if(!customer.isRecommendationActive()){
            // Throws InActiveRecommendationException if the RecommendationActive flag is not set.
            throw new InActiveRecommendationException();
        }
        // If customer found
        if(customer.getRecommendations() != null){
            // Returning all if 1) Count is greater than existing records OR 2) count is 0 or less .
            if(count < 1 || customer.getRecommendations().size() < count){
                return customer.getRecommendations();
            }else{
                // If the count is less than the records, return sublist.
                return customer.getRecommendations().subList(0, count);
            }
        }
        return null;
    }

}
