package com.spaceboost.challenge.framework.configuration;

import com.spaceboost.challenge.framework.repository.adgroup.AdGroupRepository;
import com.spaceboost.challenge.framework.repository.adgroup.IAdGroupRepository;
import com.spaceboost.challenge.framework.repository.campaign.CampainRepository;
import com.spaceboost.challenge.framework.repository.campaign.ICampainRepository;
import com.spaceboost.challenge.framework.repository.keyword.IKeywordRepository;
import com.spaceboost.challenge.framework.repository.keyword.KeywordRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfiguration {

    @Bean
    public ICampainRepository iCampainRepository(){
        return new CampainRepository();
    }

    @Bean
    public IKeywordRepository iKeywordRepository(){
        return new KeywordRepository();
    }

    @Bean
    public IAdGroupRepository iAdGroupRepository(){
        return new AdGroupRepository();
    }
}
