package com.springboot.gamereco.domain;


import javax.persistence.*;

/**
 * Recommendation POJO
 */
@Entity
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name="customerNumber")
    private Customer customer;

    private String productName;

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public Customer getCustomer() {

        return customer;
    }

    public void setCustomer(Customer customer) {

        this.customer = customer;
    }

    public String getProductName() {

        return productName;
    }

    public void setProductName(String productName) {

        this.productName = productName;
    }
}
