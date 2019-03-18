package com.spaceboost.challenge.framework.configuration;

import com.spaceboost.challenge.domain.adgroup.AdGroupService;
import com.spaceboost.challenge.framework.repository.IAdGroupRepository;
import com.spaceboost.challenge.domain.adgroup.IAdGroupService;
import com.spaceboost.challenge.domain.campain.CampainService;
import com.spaceboost.challenge.framework.repository.ICampainRepository;
import com.spaceboost.challenge.domain.campain.ICampainService;
import com.spaceboost.challenge.framework.repository.IKeywordRepository;
import com.spaceboost.challenge.domain.keyword.IKeywordService;
import com.spaceboost.challenge.domain.keyword.KeywordService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Bean
    public ICampainService iCampainService(ICampainRepository iCampainRepository) {
        return new CampainService(iCampainRepository);
    }

    @Bean
    public IAdGroupService iAdGroupService(IAdGroupRepository iAdGroupRepository, ICampainService iCampainService) {
        return new AdGroupService(iAdGroupRepository, iCampainService);
    }

    @Bean
    public IKeywordService iKeywordService(IKeywordRepository iKeywordRepository, IAdGroupService iAdGroupService) {
        return new KeywordService(iKeywordRepository, iAdGroupService);
    }
}
