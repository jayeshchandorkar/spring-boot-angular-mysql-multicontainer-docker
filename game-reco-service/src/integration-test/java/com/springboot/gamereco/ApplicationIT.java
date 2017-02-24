package com.springboot.gamereco;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.springboot.gamereco.domain.Customer;
import com.springboot.gamereco.domain.Recommendation;
import com.springboot.gamereco.repository.CustomerRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationIT {

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private CustomerRepository customerRepository;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/");
        Customer customer = new Customer();
        customer.setCustomerNumber(123);
        customer.setRecommendationActive(true);
        customer.setRecommendations(new ArrayList<Recommendation>());
        Recommendation recommendation = new Recommendation();
        recommendation.setProductName("Rec 1");
        recommendation.setCustomer(customer);
        customer.getRecommendations().add(recommendation);
        Recommendation recommendationX = new Recommendation();
        recommendationX.setProductName("Rec 1");
        recommendationX.setCustomer(customer);
        customer.getRecommendations().add(recommendationX);

        Customer customer1 = new Customer();
        customer1.setCustomerNumber(321);
        customer1.setRecommendationActive(false);
        Recommendation recommendation1 = new Recommendation();
        recommendation1.setProductName("Rec 11");
        recommendation1.setCustomer(customer1);
        customer1.setRecommendations(Collections.singletonList(recommendation1));

        customerRepository.save(customer);
        customerRepository.save(customer1);
    }

    @After
    public void cleanUp() throws Exception {
        customerRepository.delete(123);
    }

    @Test
    public void customerNotFound() throws Exception {
        ResponseEntity<String> response = template.getForEntity(base.toString() + "customers/98765432/games/recommendations?count=5",
                String.class);
        // Returns 404 status code if the customer with given customer number does not exist.
        assertThat(response.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
    }

    @Test
    public void recommendationInActive() throws Exception {
        ResponseEntity<String> response = template.getForEntity(base.toString() + "customers/321/games/recommendations?count=5",
                String.class);
        // Returns 404 status code if the recommendation flag is not set.
        assertThat(response.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
    }

    @Test
    public void getRecommendationForCustomerWithCount() throws Exception {

        ResponseEntity<String> response = template.getForEntity(base.toString() + "customers/123/games/recommendations?count=1",
                String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode responseJson = objectMapper.readTree(response.getBody());
        ArrayNode recommendations = (ArrayNode) responseJson;

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat( recommendations.size(), equalTo(1));
    }

    @Test
    public void getAllCustomers() throws Exception {

        ResponseEntity<String> response = template.getForEntity(base.toString() + "customers",
                String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode responseJson = objectMapper.readTree(response.getBody());
        ArrayNode customers = (ArrayNode) responseJson;

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat( customers.size(), equalTo(2));
        assert( customers.get(0).get("customerNumber").canConvertToLong());
        assertThat( customers.get(0).get("customerNumber").toString(), equalTo("123"));
    }



}
