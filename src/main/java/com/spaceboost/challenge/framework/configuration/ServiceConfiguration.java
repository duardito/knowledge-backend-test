package com.spaceboost.challenge.framework.configuration;

import com.spaceboost.challenge.domain.adgroup.AdGroupService;
import com.spaceboost.challenge.domain.adgroup.IAdGroupService;
import com.spaceboost.challenge.domain.campain.CampaignService;
import com.spaceboost.challenge.domain.campain.ICampainService;
import com.spaceboost.challenge.domain.compound.IKeywordAdGroupService;
import com.spaceboost.challenge.domain.compound.KeywordAdGroupService;
import com.spaceboost.challenge.domain.keyword.IKeywordService;
import com.spaceboost.challenge.domain.keyword.KeywordService;
import com.spaceboost.challenge.framework.repository.adgroup.IAdGroupRepository;
import com.spaceboost.challenge.framework.repository.campaign.ICampaignRepository;
import com.spaceboost.challenge.framework.repository.keyword.IKeywordRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Bean
    public ICampainService iCampainService(ICampaignRepository iCampaignRepository) {
        return new CampaignService(iCampaignRepository);
    }

    @Bean
    public IAdGroupService iAdGroupService(IAdGroupRepository iAdGroupRepository, ICampainService iCampainService) {
        return new AdGroupService(iAdGroupRepository, iCampainService);
    }

    @Bean
    public IKeywordService iKeywordService(IKeywordRepository iKeywordRepository, IAdGroupService iAdGroupService) {
        return new KeywordService(iKeywordRepository, iAdGroupService);
    }

    @Bean
    public IKeywordAdGroupService iKeywordAdGroupService(IKeywordService iKeywordService, IAdGroupService iAdGroupService){
        return new KeywordAdGroupService(iKeywordService,iAdGroupService);
    }
}
