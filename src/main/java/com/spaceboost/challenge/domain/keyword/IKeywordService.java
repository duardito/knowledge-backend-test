package com.spaceboost.challenge.domain.keyword;

import com.spaceboost.challenge.framework.api.RequestKeyword;

public interface IKeywordService {

    KeywordDto create(RequestKeyword requestKeyword);

    KeywordDto getByIdByCampaignAndByGroup(Long campaignId, Long adGroupId, Long keywordId);
}
