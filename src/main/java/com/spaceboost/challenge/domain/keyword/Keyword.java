package com.spaceboost.challenge.domain.keyword;


public class Keyword {

    private Long id;
    private Long campaignId;
    private Long adGroupId;
    private Integer clicks;
    private Integer conversions;
    private Double cost;

    public Keyword(){}

    private Keyword(Long id, Long campaignId, Long adGroupId, Integer clicks, Integer conversions, Double cost) {
        this.id = id;
        this.campaignId = campaignId;
        this.adGroupId = adGroupId;
        this.clicks = clicks;
        this.conversions = conversions;
        this.cost = cost;
    }

    public Keyword create(Long id, Long campaignId, Long adGroupId, Integer clicks, Integer conversions, Double cost) {
        return new Keyword(id, campaignId, adGroupId, clicks, conversions, cost);
    }

    public Long getId() {
        return id;
    }

    public  Long getCampaignId() {
        return campaignId;
    }

    public Long getAdGroupId() {
        return adGroupId;
    }

    public Integer getClicks() {
        return clicks;
    }

    public  Integer getConversions() {
        return conversions;
    }

    public Double getCost() {
        return cost;
    }
}
