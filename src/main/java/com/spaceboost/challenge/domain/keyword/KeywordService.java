package com.spaceboost.challenge.domain.keyword;

import com.spaceboost.challenge.domain.adgroup.AdGroupDto;
import com.spaceboost.challenge.domain.adgroup.IAdGroupService;
import com.spaceboost.challenge.domain.exception.ForbiddenException;
import com.spaceboost.challenge.framework.api.request.RequestKeyword;
import com.spaceboost.challenge.framework.repository.keyword.IKeywordRepository;

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
        return get(keyword);
    }

    @Override
    public KeywordDto getByIdByCampaignAndByGroup(Long campaignId, Long adGroupId, Long keywordId) {
        Keyword keyword = iKeywordRepository.findByIdByCampaignAndByGroup(campaignId, adGroupId, keywordId);
        if(keyword == null){
            return null;
        }
        return get(keyword);
    }

    @Override
    public KeywordDto getMostConverted() {
        Keyword keyword = iKeywordRepository.findMostConverted();
        return get(keyword);
    }

    @Override
    public KeywordDto getMostCostLessConverted() {
        Keyword most = iKeywordRepository.findMostCostLessConverted();
        return get(most);
    }

    private KeywordDto get(Keyword keyword) {
        return new KeywordDto.Builder().
                adGroupId(keyword.getAdGroupId()).
                campaignId(keyword.getCampaignId()).
                clicks(keyword.getClicks()).
                conversions(keyword.getConversions()).
                cost(keyword.getCost()).id(keyword.getId()).build();
    }

    private void validateAdGroupExists(Long id) {
        AdGroupDto adGroup = iAdGroupService.getBy(id);
        if(adGroup == null){
            throw new ForbiddenException("AdGroup not found");
        }
    }

}
