package com.spaceboost.challenge.domain.adgroup;


public class AdGroupDto {

    private Long id;
    private Long campaignId;
    private Integer clicks;
    private Integer conversions;
    private Double cost;

    public static class Builder {

        private Long id;
        private Long campaignId;
        private Integer clicks;
        private Integer conversions;
        private Double cost;

        public Builder id(Long val) {
            this.id = val;
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

        public AdGroupDto build() {
            return new AdGroupDto(this);
        }
    }

    private AdGroupDto(Builder builder) {
        this.id = builder.id;
        this.campaignId = builder.campaignId;
        this.clicks = builder.clicks;
        this.conversions = builder.conversions;
        this.cost = builder.cost;
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
