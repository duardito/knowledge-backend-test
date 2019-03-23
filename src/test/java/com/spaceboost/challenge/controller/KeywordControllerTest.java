package com.spaceboost.challenge.controller;

import com.google.gson.Gson;
import com.spaceboost.challenge.framework.Application;
import com.spaceboost.challenge.framework.api.request.RequestKeyword;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class KeywordControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void should_get_a_keyword() throws Exception {

        this.mockMvc.perform(
                get("/campaigns/3/adGroups/7/keywords/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
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
