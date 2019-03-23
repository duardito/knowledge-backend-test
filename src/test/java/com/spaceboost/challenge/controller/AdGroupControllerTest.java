package com.spaceboost.challenge.controller;

import com.google.gson.Gson;
import com.spaceboost.challenge.BaseTest;
import com.spaceboost.challenge.framework.api.request.RequestAdGroup;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AdGroupControllerTest extends BaseTest {

    @Test
    public void should_get_an_adGroup() throws Exception {
        this.mockMvc.perform(
                get("/adGroup/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.campaignId").value(4L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.clicks").value(83))
                .andExpect(MockMvcResultMatchers.jsonPath("$.conversions").value(17))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cost").value(1.43))
                .andReturn();
    }

    @Test
    public void should_get_an_error_adGroup_not_exists() throws Exception {
        this.mockMvc.perform(
                get("/adGroup/100")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    public void should_fail_create_an_adGroup_campaign_not_exists() throws Exception {

        RequestAdGroup requestAdGroup = new RequestAdGroup();
        requestAdGroup.setId(200L);
        requestAdGroup.setCampaignId(2000L);
        requestAdGroup.setClicks(2);
        requestAdGroup.setCost(12.4);
        requestAdGroup.setConversions(2);

        final Gson gson = new Gson();
        String json = gson.toJson(requestAdGroup);

        this.mockMvc.perform(
                post("/adGroup/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isForbidden());

    }

    @Test
    public void should_create_an_adGroup() throws Exception {

        RequestAdGroup requestAdGroup = new RequestAdGroup();
        requestAdGroup.setId(200L);
        requestAdGroup.setCampaignId(2L);
        requestAdGroup.setClicks(2);
        requestAdGroup.setCost(12.4);
        requestAdGroup.setConversions(2);

        final Gson gson = new Gson();
        String json = gson.toJson(requestAdGroup);

        this.mockMvc.perform(
                post("/adGroup/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(200L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.campaignId").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.clicks").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.conversions").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cost").value(12.4));

    }
}
