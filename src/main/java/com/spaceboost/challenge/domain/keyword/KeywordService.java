package com.spaceboost.challenge.domain.keyword;

import com.spaceboost.challenge.domain.adgroup.AdGroupDto;
import com.spaceboost.challenge.domain.adgroup.IAdGroupService;
import com.spaceboost.challenge.domain.campain.CampaingDto;
import com.spaceboost.challenge.domain.campain.ICampainService;
import com.spaceboost.challenge.infraestructure.exception.ForbiddenException;
import com.spaceboost.challenge.framework.api.request.RequestKeyword;
import com.spaceboost.challenge.framework.repository.keyword.IKeywordRepository;

public class KeywordService implements IKeywordService {

    private final IKeywordRepository iKeywordRepository;
    private final IAdGroupService iAdGroupService;
    private final ICampainService iCampainService;

    public KeywordService(IKeywordRepository iKeywordRepository, IAdGroupService iAdGroupService, ICampainService iCampainService) {
        this.iKeywordRepository = iKeywordRepository;
        this.iAdGroupService = iAdGroupService;
        this.iCampainService = iCampainService;
    }

    @Override
    public KeywordDto getMostClicked(){
        final Keyword keyword = iKeywordRepository.findMostClicked();
        return get(keyword);
    }

    @Override
    public KeywordDto create(RequestKeyword requestKeyword) {
        validateCampaignExists(requestKeyword.getCampaignId());
        validateAdGroupExists(requestKeyword.getAdGroupId());
        final Keyword keyword = iKeywordRepository.create(requestKeyword);
        return get(keyword);
    }

    @Override
    public KeywordDto getByIdByCampaignAndByGroup(Long campaignId, Long adGroupId, Long keywordId) {
        final Keyword keyword = iKeywordRepository.findByIdByCampaignAndByGroup(campaignId, adGroupId, keywordId);
        if(keyword == null){
            return null;
        }
        return get(keyword);
    }

    @Override
    public KeywordDto getMostConverted() {
        final Keyword keyword = iKeywordRepository.findMostConverted();
        return get(keyword);
    }

    @Override
    public KeywordDto getMostCostLessConverted() {
        final Keyword most = iKeywordRepository.findMostCostLessConverted();
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
        final AdGroupDto adGroup = iAdGroupService.getBy(id);
        if(adGroup == null){
            throw new ForbiddenException("AdGroup not found");
        }
    }

    private void validateCampaignExists(Long id){
        final CampaingDto campaign = iCampainService.getBy(id);
        if(campaign == null){
            throw new ForbiddenException("Campaign not found");
        }
    }

}
