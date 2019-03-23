package com.spaceboost.challenge.controller;

import com.spaceboost.challenge.BaseTest;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CampaignControllerTest extends BaseTest {

    @Test
    public void should_get_a_campaign() throws Exception {

        this.mockMvc.perform(
                get("/campaigns/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void should_get_an_error_campaign_not_exists() throws Exception {

        this.mockMvc.perform(
                get("/campaigns/100")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();
    }

}
