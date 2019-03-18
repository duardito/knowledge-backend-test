package com.spaceboost.challenge;

import com.spaceboost.challenge.domain.exception.ObjectNotFoundException;
import com.spaceboost.challenge.domain.keyword.IKeywordService;
import com.spaceboost.challenge.domain.keyword.KeywordDto;
import com.spaceboost.challenge.framework.Application;
import com.spaceboost.challenge.framework.api.RequestKeyword;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class KeywordTest {

    @Autowired
    private IKeywordService iKeywordService;

    @Test
    public void should_add_a_keyword() {
        RequestKeyword request = new RequestKeyword();
        request.setAdGroupId(1L);
        request.setCampaignId(1L);
        request.setClicks(1);
        request.setConversions(1);
        request.setCost(12.00);

        KeywordDto created = iKeywordService.create(request);
        Assert.assertEquals(request.getId(), created.getId());
        Assert.assertEquals(request.getAdGroupId(), created.getAdGroupId());
        Assert.assertEquals(request.getCampaignId(), created.getCampaignId());
        Assert.assertEquals(request.getClicks(), created.getClicks());
        Assert.assertEquals(request.getConversions(), created.getConversions());
        Assert.assertEquals(request.getCost(), created.getCost());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void should_not_create_a_keyword_adgroup_not_exists() {
        RequestKeyword request = new RequestKeyword();
        request.setAdGroupId(100L);
        request.setCampaignId(1L);
        request.setClicks(1);
        request.setConversions(1);
        request.setCost(12.00);
        iKeywordService.create(request);
    }

    @Test
    public void should_get_a_keyword() {

        KeywordDto created = iKeywordService.getByIdByCampaignAndByGroup(2L,11L,7L);
        Assert.assertEquals(7L, created.getId().longValue());
        Assert.assertEquals(11L, created.getAdGroupId().longValue());
        Assert.assertEquals(2L, created.getCampaignId().longValue());
        Assert.assertEquals(6, created.getClicks().intValue());
        Assert.assertEquals(1, created.getConversions().intValue());
        Assert.assertEquals(new Double(0.89), created.getCost());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void should_not_find_a_keyword_even_adgroup_and_campain_exists() {

        iKeywordService.getByIdByCampaignAndByGroup(2L,11L,1000L);
    }
}
