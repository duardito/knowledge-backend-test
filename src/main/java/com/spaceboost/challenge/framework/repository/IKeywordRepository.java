package com.spaceboost.challenge.framework.repository;

import com.spaceboost.challenge.domain.keyword.Keyword;
import com.spaceboost.challenge.framework.api.request.RequestKeyword;

public interface IKeywordRepository {
    Keyword find(Long id);

    Keyword findByIdByCampaignAndByGroup(Long campaignId, Long adGroupId, Long keywordId);

    Keyword create(RequestKeyword request);

    Keyword findMostConverted();

    Keyword findMostClicked();

    Keyword findMostCostLessConverted();
}
