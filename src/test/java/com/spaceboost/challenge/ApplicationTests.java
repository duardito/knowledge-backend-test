package com.spaceboost.challenge;

import com.google.gson.Gson;
import com.spaceboost.challenge.domain.adgroup.AdGroupDto;
import com.spaceboost.challenge.domain.adgroup.AdGroupService;
import com.spaceboost.challenge.domain.adgroup.IAdGroupService;
import com.spaceboost.challenge.domain.exception.DuplicatedKeyException;
import com.spaceboost.challenge.domain.exception.ObjectNotFoundException;
import com.spaceboost.challenge.framework.Application;
import com.spaceboost.challenge.framework.api.AdGroupController;
import com.spaceboost.challenge.framework.api.CampaignController;
import com.spaceboost.challenge.framework.api.KeywordController;
import com.spaceboost.challenge.framework.api.RequestAdGroup;
import com.spaceboost.challenge.framework.configuration.ErrorHandling;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.ExceptionHandlerMethodResolver;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod;

import java.lang.reflect.Method;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTests {

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
        Assert.assertEquals(requestAdGroup.getId(),created.getId());
        Assert.assertEquals(requestAdGroup.getCampaignId(),created.getCampaignId());
        Assert.assertEquals(requestAdGroup.getClicks(),created.getClicks());
        Assert.assertEquals(requestAdGroup.getConversions(),created.getConversions());
        Assert.assertEquals(requestAdGroup.getCost(),created.getCost());
    }

}
