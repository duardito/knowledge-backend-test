package com.spaceboost.challenge.domain.campain;

import com.spaceboost.challenge.framework.repository.campaign.ICampainRepository;

public class CampainService implements ICampainService {

    private final ICampainRepository iCampainRepository;

    public CampainService(ICampainRepository iCampainRepository) {
        this.iCampainRepository = iCampainRepository;
    }

    @Override
    public CampainDto getBy(Long id){
        Campaign campaign = iCampainRepository.find(id);
        return new CampainDto.Builder().id(campaign.getId()).build();
    }



    @Override
    public CampainDto create(Long id){
        Campaign campaign =  iCampainRepository.create(id);
        return new CampainDto.Builder().id(campaign.getId()).build();
    }

    @Override
    public CampainDto getWithMostCostButLessConversions() {
        return null;
    }
}
