package com.spaceboost.challenge.domain.adgroup;

public class AdGroup {

    private Long id;
    private Long campaignId;
    private Integer clicks;
    private Integer conversions;
    private Double cost;

    public AdGroup() {
    }

    private AdGroup(Long id, Long campaignId, Integer clicks, Integer conversions, Double cost) {
        this.id = id;
        this.campaignId = campaignId;
        this.clicks = clicks;
        this.conversions = conversions;
        this.cost = cost;
    }

    public AdGroup create(Long id,Long campaignId, Integer clicks, Integer conversions, Double cost) {
        return new AdGroup(id, campaignId, clicks, conversions, cost);
    }

    public Long getId() {
        return id;
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public Integer getClicks() {
        return clicks;
    }

    public Integer getConversions() {
        return conversions;
    }

    public Double getCost() {
        return cost;
    }


}
