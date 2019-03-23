package com.spaceboost.challenge.domain.keyword;

import com.spaceboost.challenge.framework.api.request.RequestKeyword;

public interface IKeywordService {

    KeywordDto getMostClicked();

    KeywordDto create(RequestKeyword requestKeyword);

    KeywordDto getByIdByCampaignAndByGroup(Long campaignId, Long adGroupId, Long keywordId);

    KeywordDto getMostConverted();

    KeywordDto getMostCostLessConverted();
}
