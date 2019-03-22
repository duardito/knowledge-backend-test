package com.spaceboost.challenge;

import com.spaceboost.challenge.domain.adgroup.AdGroupDto;
import com.spaceboost.challenge.domain.adgroup.IAdGroupService;
import com.spaceboost.challenge.domain.exception.DuplicatedKeyException;
import com.spaceboost.challenge.domain.exception.ObjectNotFoundException;
import com.spaceboost.challenge.framework.api.RequestAdGroup;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class AdGroupTests extends BaseTest{

    @Autowired
    private IAdGroupService adGroupService;

    @Test(expected = DuplicatedKeyException.class)
    public void should_get_error_duplicated_key() throws Exception {
        RequestAdGroup requestAdGroup = new RequestAdGroup();
        requestAdGroup.setId(1L);
        requestAdGroup.setCampaignId(1L);

        adGroupService.create(requestAdGroup);
    }


    @Test(expected = ObjectNotFoundException.class)
    public void should_get_error_campaign_not_exists() throws Exception {
        RequestAdGroup requestAdGroup = new RequestAdGroup();
        requestAdGroup.setId(400L);
        requestAdGroup.setCampaignId(100L);

        adGroupService.create(requestAdGroup);
    }

    @Test
    public void should_create_an_adgroup() throws Exception {
        RequestAdGroup requestAdGroup = new RequestAdGroup();
        requestAdGroup.setId(20L);
        requestAdGroup.setCampaignId(1L);
        requestAdGroup.setClicks(2);
        requestAdGroup.setConversions(1);
        requestAdGroup.setCost(1.00);

        AdGroupDto created = adGroupService.create(requestAdGroup);
        Assert.assertEquals(requestAdGroup.getId(), created.getId());
        Assert.assertEquals(requestAdGroup.getCampaignId(), created.getCampaignId());
        Assert.assertEquals(requestAdGroup.getClicks(), created.getClicks());
        Assert.assertEquals(requestAdGroup.getConversions(), created.getConversions());
        Assert.assertEquals(requestAdGroup.getCost(), created.getCost());

    }

    @Test(expected = ObjectNotFoundException.class)
    public void should_find_ad_adgroup() {
        adGroupService.getBy(1000L);
    }
}
