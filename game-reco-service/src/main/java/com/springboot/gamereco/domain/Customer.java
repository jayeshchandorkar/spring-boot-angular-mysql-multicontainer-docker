package com.springboot.gamereco.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Customer POJO
 */
@Entity
public class Customer {

    @Id
    private long customerNumber;

    private boolean recommendationActive;

    @OneToMany(mappedBy="customer", cascade = CascadeType.ALL, orphanRemoval=true)
    private List<Recommendation> recommendations;

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

    public List<Recommendation> getRecommendations() {

        return recommendations;
    }

    public void setRecommendations(List<Recommendation> recommendations) {

        this.recommendations = recommendations;
    }

}
