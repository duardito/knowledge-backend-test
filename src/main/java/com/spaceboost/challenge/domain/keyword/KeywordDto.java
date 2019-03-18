package com.spaceboost.challenge.domain.keyword;


import java.io.Serializable;

public class KeywordDto implements Serializable {

    private Long id;
    private Long campaignId;
    private Long adGroupId;
    private Integer clicks;
    private Integer conversions;
    private Double cost;

    public static class Builder {

        private Long id;
        private Long campaignId;
        private Long adGroupId;
        private Integer clicks;
        private Integer conversions;
        private Double cost;

        public Builder id(Long val) {
            this.id = val;
            return this;
        }

        public Builder adGroupId(Long val) {
            this.adGroupId = val;
            return this;
        }

        public Builder campaignId(Long val) {
            this.campaignId = val;
            return this;
        }

        public Builder clicks(Integer val) {
            this.clicks = val;
            return this;
        }

        public Builder conversions(Integer val) {
            this.conversions = val;
            return this;
        }

        public Builder cost(Double val) {
            this.cost = val;
            return this;
        }
        public KeywordDto build() {
            return new KeywordDto(this);
        }

    }

    private KeywordDto(Builder builder) {
        this.adGroupId = builder.adGroupId;
        this.campaignId = builder.campaignId;
        this.clicks = builder.clicks;
        this.conversions = builder.conversions;
        this.cost = builder.cost;
        this.id = builder.id;
    }

    public Long getId() {
        return id;
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public Long getAdGroupId() {
        return adGroupId;
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
