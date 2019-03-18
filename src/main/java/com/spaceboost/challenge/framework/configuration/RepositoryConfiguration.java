package com.spaceboost.challenge.framework.configuration;

import com.spaceboost.challenge.framework.repository.AdGroupRepository;
import com.spaceboost.challenge.framework.repository.IAdGroupRepository;
import com.spaceboost.challenge.framework.repository.CampainRepository;
import com.spaceboost.challenge.framework.repository.ICampainRepository;
import com.spaceboost.challenge.framework.repository.IKeywordRepository;
import com.spaceboost.challenge.framework.repository.KeywordRepository;
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