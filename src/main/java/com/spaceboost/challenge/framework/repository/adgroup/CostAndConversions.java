package com.spaceboost.challenge.framework.repository.adgroup;

public class CostAndConversions {

    public Integer conversions;
    public Double cost;
    public Long id;
    public Long campaignId;
    public Integer clicks;

    public CostAndConversions(){
        conversions = 0;
        cost = 0.00;
    }

    public Integer getConversions() {
        return conversions;
    }

    public Double getCost() {
        return cost;
    }
}
