package com.spaceboost.challenge.framework.api.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class RequestAdGroup {

    @PositiveOrZero
    @NotNull
    private Long id;

    @PositiveOrZero
    @NotNull
    private Long campaignId;

    @PositiveOrZero
    @NotNull
    private Integer clicks;

    @PositiveOrZero
    @NotNull
    private Integer conversions;

    @PositiveOrZero
    @NotNull
    private Double cost;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    public Integer getClicks() {
        return clicks;
    }

    public void setClicks(Integer clicks) {
        this.clicks = clicks;
    }

    public Integer getConversions() {
        return conversions;
    }

    public void setConversions(Integer conversions) {
        this.conversions = conversions;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
}
