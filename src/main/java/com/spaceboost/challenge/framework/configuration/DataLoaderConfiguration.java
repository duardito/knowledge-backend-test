package com.spaceboost.challenge.framework.configuration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spaceboost.challenge.domain.adgroup.IAdGroupService;
import com.spaceboost.challenge.domain.campain.Campaign;
import com.spaceboost.challenge.domain.campain.ICampainService;
import com.spaceboost.challenge.domain.keyword.IKeywordService;
import com.spaceboost.challenge.framework.api.request.RequestAdGroup;
import com.spaceboost.challenge.framework.api.request.RequestKeyword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class DataLoaderConfiguration implements CommandLineRunner {

    @Autowired
    private ICampainService iCampainService;

    @Autowired
    private IAdGroupService iAdGroupService;

    @Autowired
    private IKeywordService iKeywordService;

    @Override
    public void run(String... args)  {
        loadCampaigns();
        loadAdGroups();
        loadKeywords();
    }

    private void loadCampaigns(){
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Campaign>> mapType = new TypeReference<List<Campaign>>() {};
        InputStream is = TypeReference.class.getResourceAsStream("/entities/campaigns.json");
        try {
            List<Campaign> stateList = mapper.readValue(is, mapType);
            stateList.stream().forEach( campaign -> iCampainService.create(campaign.getId()));

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void loadAdGroups(){
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<RequestAdGroup>> mapType = new TypeReference<List<RequestAdGroup>>() {};
        InputStream is = TypeReference.class.getResourceAsStream("/entities/adGroups.json");
        try {
            List<RequestAdGroup> stateList = mapper.readValue(is, mapType);
            stateList.stream().forEach( adGroup-> iAdGroupService.create(adGroup));

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void loadKeywords(){
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<RequestKeyword>> mapType = new TypeReference<List<RequestKeyword>>() {};
        InputStream is = TypeReference.class.getResourceAsStream("/entities/keywords.json");
        try {
            List<RequestKeyword> stateList = mapper.readValue(is, mapType);
            stateList.stream().forEach( keyword-> iKeywordService.create(keyword));

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
