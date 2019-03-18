package com.spaceboost.challenge.domain.campain;

public class CampainDto {

    private Long id;

    public static class Builder {

        private Long id;

        public Builder id(Long val) {
            this.id = val;
            return this;
        }

        public CampainDto build() {
            return new CampainDto(this);
        }
    }

    private CampainDto(Builder builder) {
        this.id = builder.id;
    }

    public Long getId() {
        return id;
    }
}
