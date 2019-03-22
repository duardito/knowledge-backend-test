package com.spaceboost.challenge;

import com.spaceboost.challenge.domain.exception.ObjectNotFoundException;
import com.spaceboost.challenge.domain.keyword.IKeywordService;
import com.spaceboost.challenge.domain.keyword.KeywordDto;
import com.spaceboost.challenge.framework.api.request.RequestKeyword;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class KeywordTest extends BaseTest{

    @Autowired
    private IKeywordService iKeywordService;

    @Test
    public void should_get_most_converted() {
        KeywordDto request = iKeywordService.getMostCostLessConverted();
        Assert.assertEquals(request.getId().longValue(), 27L);
        Assert.assertEquals(request.getAdGroupId().longValue(), 3L);
        Assert.assertEquals(request.getCampaignId().longValue(),0L);
        Assert.assertEquals(request.getClicks().intValue(), 11);
        Assert.assertEquals(request.getConversions().intValue(), 9);
        Assert.assertEquals(request.getCost(), new Double(47.44));
    }

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

    @Test
    public void should_get_most_converted_keyword() {
        KeywordDto created = iKeywordService.getMostConverted();
        Assert.assertEquals(27L, created.getId().longValue());
        Assert.assertEquals(3L, created.getAdGroupId().longValue());
        Assert.assertEquals(0L, created.getCampaignId().longValue());
        Assert.assertEquals(11, created.getClicks().intValue());
        Assert.assertEquals(5, created.getConversions().intValue());
        Assert.assertEquals(new Double(9.11), created.getCost());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void should_not_find_a_keyword_even_adgroup_and_campain_exists() {

        iKeywordService.getByIdByCampaignAndByGroup(2L,11L,1000L);
    }


}
