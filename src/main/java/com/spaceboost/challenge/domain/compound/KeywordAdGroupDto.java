package com.spaceboost.challenge.domain.compound;

import com.spaceboost.challenge.domain.adgroup.AdGroupDto;
import com.spaceboost.challenge.domain.keyword.KeywordDto;

public class KeywordAdGroupDto {

    private KeywordDto keyword;
    private AdGroupDto adGroup;

    public static class Builder {
        private KeywordDto keyword;
        private AdGroupDto adGroup;

        public Builder keyword(KeywordDto val) {
            this.keyword = val;
            return this;
        }

        public Builder adGroup(AdGroupDto val) {
            this.adGroup = val;
            return this;
        }

        public KeywordAdGroupDto build(){
            return new KeywordAdGroupDto(this);
        }
    }

    private KeywordAdGroupDto(Builder builder) {
        this.keyword = builder.keyword;
        this.adGroup = builder.adGroup;
    }

    public KeywordDto getKeyword() {
        return keyword;
    }

    public AdGroupDto getAdGroup() {
        return adGroup;
    }
}
