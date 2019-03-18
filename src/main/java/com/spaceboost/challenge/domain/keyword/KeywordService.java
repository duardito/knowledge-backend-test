package com.spaceboost.challenge.domain.keyword;

import com.spaceboost.challenge.domain.adgroup.IAdGroupService;
import com.spaceboost.challenge.framework.api.RequestKeyword;
import com.spaceboost.challenge.framework.repository.IKeywordRepository;

public class KeywordService implements IKeywordService {

    private final IKeywordRepository iKeywordRepository;
    private final IAdGroupService iAdGroupService;

    public KeywordService(IKeywordRepository iKeywordRepository, IAdGroupService iAdGroupService) {
        this.iKeywordRepository = iKeywordRepository;
        this.iAdGroupService = iAdGroupService;
    }

    @Override
    public KeywordDto create(RequestKeyword requestKeyword) {
        validateAdGroupExists(requestKeyword.getAdGroupId());
        Keyword keyword = iKeywordRepository.create(requestKeyword);
        return new KeywordDto.Builder().
                adGroupId(keyword.getAdGroupId()).
                campaignId(keyword.getCampaignId()).
                clicks(keyword.getClicks()).
                conversions(keyword.getConversions()).
                cost(keyword.getCost()).id(keyword.getId()).build();
    }

    @Override
    public KeywordDto getByIdByCampaignAndByGroup(Long campaignId, Long adGroupId, Long keywordId) {
        Keyword keyword = iKeywordRepository.findByIdByCampaignAndByGroup(campaignId, adGroupId, keywordId);
        return new KeywordDto.Builder().
                adGroupId(keyword.getAdGroupId()).
                campaignId(keyword.getCampaignId()).
                clicks(keyword.getClicks()).
                conversions(keyword.getConversions()).
                cost(keyword.getCost()).id(keyword.getId()).build();
    }

    private void validateAdGroupExists(Long id){
        iAdGroupService.getBy(id);
    }

}