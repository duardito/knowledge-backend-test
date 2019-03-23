package com.spaceboost.challenge.domain.campain;

import com.spaceboost.challenge.framework.repository.campaign.ICampaignRepository;

public class CampaignService implements ICampainService {

    private final ICampaignRepository iCampaignRepository;

    public CampaignService(ICampaignRepository iCampaignRepository) {
        this.iCampaignRepository = iCampaignRepository;
    }

    @Override
    public CampainDto getBy(Long id){
        Campaign campaign = iCampaignRepository.find(id);
        if(campaign == null){
            return null;
        }
        return new CampainDto.Builder().id(campaign.getId()).build();
    }



    @Override
    public CampainDto create(Long id){
        Campaign campaign =  iCampaignRepository.create(id);
        return new CampainDto.Builder().id(campaign.getId()).build();
    }

}
