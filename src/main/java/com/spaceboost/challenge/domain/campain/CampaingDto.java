package com.spaceboost.challenge.domain.campain;

public class CampaingDto {

    private Long id;

    public static class Builder {

        private Long id;

        public Builder id(Long val) {
            this.id = val;
            return this;
        }

        public CampaingDto build() {
            return new CampaingDto(this);
        }
    }

    private CampaingDto(Builder builder) {
        this.id = builder.id;
    }

    public Long getId() {
        return id;
    }
}
