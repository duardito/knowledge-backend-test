package com.spaceboost.challenge.controller;

import com.google.gson.Gson;
import com.spaceboost.challenge.BaseTest;
import com.spaceboost.challenge.framework.api.request.RequestKeyword;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class KeywordControllerTest extends BaseTest {

    @Test
    public void should_get_a_keyword() throws Exception {

        this.mockMvc.perform(
                get("/campaigns/3/adGroups/7/keywords/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.campaignId").value(3L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.adGroupId").value(7L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.clicks").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.conversions").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cost").value(2.07))
                .andReturn();
    }

    @Test
    public void should_get_keyword_most_clicked() throws Exception {

        this.mockMvc.perform(
                get("/keywords/getMostClicked")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(27L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.campaignId").value(0L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.adGroupId").value(3L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.clicks").value(11))
                .andExpect(MockMvcResultMatchers.jsonPath("$.conversions").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cost").value(9.11))
                .andReturn();
    }


    @Test
    public void should_get_keyword_most_converted() throws Exception {

        this.mockMvc.perform(
                get("/keywords/getMostConverted")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(27L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.campaignId").value(0L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.adGroupId").value(3L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.clicks").value(11))
                .andExpect(MockMvcResultMatchers.jsonPath("$.conversions").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cost").value(9.11))
                .andReturn();
    }

    @Test
    public void should_get_an_error_keyword_not_exists() throws Exception {

        this.mockMvc.perform(
                get("/campaigns/100/adGroups/1/keywords/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    public void should_get_an_error_when_get_a_keyword_addGroup_not_exists() throws Exception {

        this.mockMvc.perform(
                get("/campaigns/1/adGroups/100/keywords/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    public void should_fail_create_keyword_adgroup_not_exists() throws Exception {

        RequestKeyword requestKeyword = new RequestKeyword();
        requestKeyword.setId(200L);
        requestKeyword.setAdGroupId(3000L);
        requestKeyword.setCampaignId(2L);

        final Gson gson = new Gson();
        String json = gson.toJson(requestKeyword);

        this.mockMvc.perform(
                post("/keywords")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isForbidden())
                .andReturn();
    }

    @Test
    public void should_fail_create_keyword_campaign_not_exists() throws Exception {

        RequestKeyword requestKeyword = new RequestKeyword();
        requestKeyword.setId(200L);
        requestKeyword.setAdGroupId(3L);
        requestKeyword.setCampaignId(250L);

        final Gson gson = new Gson();
        String json = gson.toJson(requestKeyword);

        this.mockMvc.perform(
                post("/keywords")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isForbidden())
                .andReturn();
    }

    @Test
    public void should_create_keyword() throws Exception {

        RequestKeyword requestKeyword = new RequestKeyword();
        requestKeyword.setId(200L);
        requestKeyword.setAdGroupId(3L);
        requestKeyword.setCampaignId(2L);
        requestKeyword.setClicks(2);
        requestKeyword.setCost(12.4);
        requestKeyword.setConversions(2);

        final Gson gson = new Gson();
        String json = gson.toJson(requestKeyword);

        this.mockMvc.perform(
                post("/keywords")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(200L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.adGroupId").value(3L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.campaignId").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.clicks").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cost").value(12.4));

    }
}
