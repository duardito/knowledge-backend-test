package com.spaceboost.challenge.domain.campain;

import com.spaceboost.challenge.framework.repository.campaign.ICampaignRepository;

public class CampaignService implements ICampainService {

    private final ICampaignRepository iCampaignRepository;

    public CampaignService(ICampaignRepository iCampaignRepository) {
        this.iCampaignRepository = iCampaignRepository;
    }

    @Override
    public CampaingDto getBy(Long id){
        final Campaign campaign = iCampaignRepository.find(id);
        if(campaign == null){
            return null;
        }
        return new CampaingDto.Builder().id(campaign.getId()).build();
    }

    @Override
    public CampaingDto create(Long id){
        final Campaign campaign =  iCampaignRepository.create(id);
        return new CampaingDto.Builder().id(campaign.getId()).build();
    }

}
