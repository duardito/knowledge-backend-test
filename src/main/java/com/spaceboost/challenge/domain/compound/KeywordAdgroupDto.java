package com.spaceboost.challenge.domain.compound;

import com.spaceboost.challenge.domain.adgroup.AdGroupDto;
import com.spaceboost.challenge.domain.keyword.KeywordDto;

public class KeywordAdgroupDto {

    private KeywordDto keywordDto;
    private AdGroupDto adGroupDto;

    public KeywordAdgroupDto(KeywordDto keywordDto, AdGroupDto adGroupDto) {
        this.keywordDto = keywordDto;
        this.adGroupDto = adGroupDto;
    }

    public KeywordDto getKeywordDto() {
        return keywordDto;
    }

    public AdGroupDto getAdGroupDto() {
        return adGroupDto;
    }
}
