package com.springboot.gamereco;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.springboot.gamereco.domain.Customer;
import com.springboot.gamereco.domain.Recommendation;
import com.springboot.gamereco.exception.CustomerNotfoundException;
import com.springboot.gamereco.exception.InActiveRecommendationException;
import com.springboot.gamereco.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    CustomerService customerServiceMock;

    @Test
    public void customerNotFound() throws Exception {
        BDDMockito.given(customerServiceMock.getRecommendations(98765432l, 5)).willThrow(new CustomerNotfoundException());
        mvc.perform(MockMvcRequestBuilders.get("/customers/98765432/games/recommendations?count=5"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void recommendationInActiveForCustomer() throws Exception {

        Customer customer = new Customer();
        customer.setRecommendationActive(false);
        customer.setCustomerNumber(12345l);

        BDDMockito.given(customerServiceMock.getRecommendations(98765432l, 5)).willThrow(new InActiveRecommendationException());
        mvc.perform(MockMvcRequestBuilders.get("/customers/98765432/games/recommendations?count=5"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getRecommendationForCustomer() throws Exception {

        List<Recommendation> recommendationList = new ArrayList<Recommendation>();

        Customer customer = new Customer();
        customer.setRecommendationActive(true);
        customer.setCustomerNumber(98765432l);
        customer.setRecommendations(recommendationList);
        Recommendation recommendation = new Recommendation();
        recommendation.setProductName("Rec 1");
        recommendation.setId(1);
        recommendation.setCustomer(customer);
        recommendationList.add(recommendation);

        Recommendation recommendation1 = new Recommendation();
        recommendation1.setProductName("Rec 2");
        recommendation1.setId(2);
        recommendation1.setCustomer(customer);
        recommendationList.add(recommendation1);

        BDDMockito.given(customerServiceMock.getRecommendations(98765432l, 1)).willReturn(recommendationList);
        mvc.perform(MockMvcRequestBuilders.get("/customers/98765432/games/recommendations?count=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].productName", is(recommendationList.get(0).getProductName())));
    }

    @Test
    public void getText() throws Exception {

        Customer customer = new Customer();
        customer.setRecommendationActive(false);
        customer.setCustomerNumber(12345);

        BDDMockito.given(customerServiceMock.getAllCustomers()).willReturn(Collections.singletonList(customer));

        mvc.perform(MockMvcRequestBuilders.get("/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].customerNumber", is(new Long(customer.getCustomerNumber()).intValue())))
                .andExpect(jsonPath("$[0].recommendationActive", is(customer.isRecommendationActive())));
    }

}
