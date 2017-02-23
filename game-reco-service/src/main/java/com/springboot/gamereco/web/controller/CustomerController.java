package com.springboot.gamereco.web.controller;

import com.springboot.gamereco.exception.CustomerNotfoundException;
import com.springboot.gamereco.domain.Customer;
import com.springboot.gamereco.domain.Recommendation;
import com.springboot.gamereco.exception.InActiveRecommendationException;
import com.springboot.gamereco.service.CustomerService;
import com.springboot.gamereco.web.controller.model.CustomerModel;
import com.springboot.gamereco.web.controller.model.RecommendationModel;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @RequestMapping(value="/customers/{customerNumber}/games/recommendations", method= RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public ResponseEntity getGameRecommendationForCustomer(@PathVariable("customerNumber") long customerNumber, @RequestParam(value="count", required=false) Integer count){
        List<RecommendationModel> recommendationModels = new ArrayList<RecommendationModel>();
        int iCount = (count != null) ? count : 0;
        try {
            // Get the recommendations for the customer.
            List<Recommendation> recommendations = customerService.getRecommendations(customerNumber, iCount);
            // Converts the recommendations into UI models.
            recommendationModels = convertToRecommendationModels(recommendations);
        }catch(Exception exception){
            // Check for customer not found and recommendation inactive exception
            if(exception instanceof CustomerNotfoundException){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer Not Found.");
            }else if (exception instanceof InActiveRecommendationException){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recommendations not activated for Customer.");
            }
        }
        // Returns recommendations
        return ResponseEntity.ok(recommendationModels);
    }

    @RequestMapping(value="/customers/{customerNumber}/games/recommendations", method=RequestMethod.POST)
    public ResponseEntity  handleRecommendationUpload(@PathVariable("customerNumber") long customerNumber, @RequestParam("file") MultipartFile multipartFile){

        if(customerNumber < 1){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Customer Number.");
        }
        if (!multipartFile.isEmpty()) {
            try {
                // Parse the csv file and get the recommendations for customers.
                List<Customer> customers = parseRecommendations(multipartFile);
                if(customers != null && !customers.isEmpty()){
                    // Add Customer recommendations.
                    customerService.addRecommendations(customers);
                }
            }catch(IOException iOException){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unable to read CSV file.");
            }
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Empty CSV file.");
        }
        return ResponseEntity.ok("");
    }

    @RequestMapping(value="/customers", method= RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public ResponseEntity getCustomers(){
        List<CustomerModel> customerModels = new ArrayList<CustomerModel>();
        try {
            // Get all customers.
            List<Customer> customers = customerService.getAllCustomers();
            // Converts the customers into UI models.
            customerModels = convertToCustomerModels(customers);
        }catch(Exception exception){
            // Throw exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while retrieving all Customers.");
        }
        // Returns customers
        return ResponseEntity.ok(customerModels);
    }

    /**
     * Method takes multipartFile as input and parse it as a CSV file.
     * Returns the list of customer instances found in csv file populated with recommendations details.
     *
     * @param multipartFile
     * @return
     * @throws IOException
     */
    private List<Customer> parseRecommendations(MultipartFile multipartFile) throws IOException{
        List<Customer> customers = new ArrayList<Customer>();
        try {
            // Create reader from multipart file.
            InputStreamReader reader = new InputStreamReader(multipartFile.getInputStream());
            // Get the CSV parser for the file.
            CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
            List<CSVRecord> csvRecords = parser.getRecords();
            if(parser != null && !csvRecords.isEmpty()){
                // Iterate through each record to create Recommendation instances.
                for(CSVRecord csvRecord : csvRecords){
                    // Create new customer instance for each record.
                    Customer customer = new Customer();
                    // Add customer record to list
                    customers.add(customer);
                    // Set the recommendations for customer.
                    customer.setRecommendations(new ArrayList<Recommendation>());
                    // Checks and set first column value as customer number
                    if(csvRecord.get(0) != null){
                        customer.setCustomerNumber(Long.parseLong(csvRecord.get(0)));
                    }
                    // Checks and set second value as recommendation active flag
                    if(csvRecord.get(1) != null){
                        customer.setRecommendationActive(Boolean.parseBoolean(csvRecord.get(1)));
                    }
                    // Iterate through remaining columns to set the recommendations.
                    for(int i = 2; i < csvRecord.size(); i++){
                        // Check if valid product name is provided
                        if(csvRecord.get(i) != null && csvRecord.get(i).length() > 0){
                            Recommendation recommendation = new Recommendation();
                            recommendation.setCustomer(customer);
                            recommendation.setProductName(csvRecord.get(i));
                            customer.getRecommendations().add(recommendation);
                        }
                    }
                }
            }
        }catch(IOException iOException){
            throw new IOException("Error while parsing CSV file." + iOException);
        }
        return customers;
    }

    /**
     * Method converts the Customer pojos to ui model
     * @param customers
     * @return
     */
    private List<CustomerModel>  convertToCustomerModels(List<Customer> customers){
        List<CustomerModel> customerModels = new ArrayList<CustomerModel>();
        if(customers != null && !customers.isEmpty()){
            for (Customer customer : customers){
                CustomerModel customerModel = new CustomerModel();
                customerModel.setCustomerNumber(customer.getCustomerNumber());
                customerModel.setRecommendationActive(customer.isRecommendationActive());
                customerModels.add(customerModel);
            }
        }
        return customerModels;
    }

    /**
     * Method converts the Recommendation pojos to ui model
     * @param recommendations
     * @return
     */
    private List<RecommendationModel>  convertToRecommendationModels(List<Recommendation> recommendations){
        List<RecommendationModel> recommendationModels = new ArrayList<RecommendationModel>();
        if(recommendations != null && !recommendations.isEmpty()){
            for (Recommendation recommendation : recommendations){
                RecommendationModel recommendationModel = new RecommendationModel();
                recommendationModel.setCustomerNumber(recommendation.getCustomer().getCustomerNumber());
                recommendationModel.setId(recommendation.getId());
                recommendationModel.setProductName(recommendation.getProductName());
                recommendationModels.add(recommendationModel);
            }
        }
        return recommendationModels;
    }
}
