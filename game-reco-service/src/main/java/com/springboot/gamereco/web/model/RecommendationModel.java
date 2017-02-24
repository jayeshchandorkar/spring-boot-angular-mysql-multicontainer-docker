package com.springboot.gamereco.web.model;

/**
 * Model for Recommendation
 */
public class RecommendationModel {

    private long id;

    private long customerNumber;

    private String productName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCustomerNumber() {

        return customerNumber;
    }

    public void setCustomerNumber(long customerNumber) {

        this.customerNumber = customerNumber;
    }

    public String getProductName() {

        return productName;
    }

    public void setProductName(String productName) {

        this.productName = productName;
    }

}