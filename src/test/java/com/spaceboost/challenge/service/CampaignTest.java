package com.spaceboost.challenge.service;


import com.spaceboost.challenge.BaseTest;
import com.spaceboost.challenge.domain.campain.CampaingDto;
import com.spaceboost.challenge.domain.campain.ICampainService;
import com.spaceboost.challenge.domain.exception.DuplicatedKeyException;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CampaignTest extends BaseTest {

    @Autowired
    private ICampainService iCampainService;

    @Test
    public void should_add_a_campaign(){
        CampaingDto campaign = iCampainService.create(10L);
        Assert.assertEquals(campaign.getId().longValue(), 10L);
    }

    @Test(expected = DuplicatedKeyException.class)
    public void should_fail_adding_an_existing_campaign(){
         iCampainService.create(1L);
    }

    @Test
    public void should_fail_getting_a_campaign_wich_not_exists(){
        CampaingDto response = iCampainService.getBy(20L);
        Assert.assertNull(response);
    }


    @Test
    public void should_get_a_campaign(){
        CampaingDto campaign = iCampainService.getBy(1L);
        Assert.assertEquals(campaign.getId().longValue(), 1L);
    }
}
