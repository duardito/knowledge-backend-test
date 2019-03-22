package com.spaceboost.challenge;


import com.spaceboost.challenge.domain.campain.CampainDto;
import com.spaceboost.challenge.domain.campain.ICampainService;
import com.spaceboost.challenge.domain.exception.DuplicatedKeyException;
import com.spaceboost.challenge.domain.exception.ObjectNotFoundException;
import com.spaceboost.challenge.framework.Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

public class CampaignTest extends BaseTest{

    @Autowired
    private ICampainService iCampainService;

    @Test
    public void should_add_a_campaign(){
        CampainDto campaign = iCampainService.create(10L);
        Assert.assertEquals(campaign.getId().longValue(), 10L);
    }

    @Test(expected = DuplicatedKeyException.class)
    public void should_fail_adding_an_existing_campaign(){
         iCampainService.create(1L);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void should_fail_getting_a_campaign_wich_not_exists(){
        iCampainService.getBy(20L);
    }


    @Test
    public void should_get_a_campaign(){
        CampainDto campaign = iCampainService.getBy(1L);
        Assert.assertEquals(campaign.getId().longValue(), 1L);
    }
}
