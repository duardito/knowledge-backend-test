package com.spaceboost.challenge.domain.adgroup;

import com.spaceboost.challenge.domain.campain.ICampainService;
import com.spaceboost.challenge.framework.api.RequestAdGroup;
import com.spaceboost.challenge.framework.repository.IAdGroupRepository;

public class AdGroupService implements IAdGroupService {

    private final IAdGroupRepository iAdGroupRepository;
    private final ICampainService iCampainService;

    public AdGroupService(IAdGroupRepository iAdGroupRepository, ICampainService iCampainService) {
        this.iAdGroupRepository = iAdGroupRepository;
        this.iCampainService = iCampainService;
    }

    @Override
    public AdGroupDto getBy(Long id) {
        AdGroup adGroup = iAdGroupRepository.find(id);
        return new AdGroupDto.Builder().
                id(adGroup.getId()).
                clicks(adGroup.getClicks()).
                campaignId(adGroup.getCampaignId()).
                cost(adGroup.getCost()).
                conversions(adGroup.getConversions()).
                build();
    }


    @Override
    public AdGroupDto create(RequestAdGroup requestAdGroup){
        validateCampaignExist(requestAdGroup.getCampaignId());
        AdGroup adGroup = iAdGroupRepository.create(requestAdGroup);
        return new AdGroupDto.Builder().
                id(adGroup.getId()).
                clicks(adGroup.getClicks()).
                campaignId(adGroup.getCampaignId()).
                cost(adGroup.getCost()).
                conversions(adGroup.getConversions()).
                build();
    }

    private void validateCampaignExist(Long id){
        iCampainService.getBy(id);
    }

}