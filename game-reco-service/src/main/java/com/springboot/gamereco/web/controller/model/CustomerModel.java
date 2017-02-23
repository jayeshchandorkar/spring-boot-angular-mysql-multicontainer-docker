package com.springboot.gamereco.web.controller.model;

/**
 * Model for Customer
 */
public class CustomerModel {

    private long customerNumber;

    private boolean recommendationActive;

    public long getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(long customerNumber) {
        this.customerNumber = customerNumber;
    }

    public boolean isRecommendationActive() {
        return recommendationActive;
    }

    public void setRecommendationActive(boolean recommendationActive) {
        this.recommendationActive = recommendationActive;
    }
}
