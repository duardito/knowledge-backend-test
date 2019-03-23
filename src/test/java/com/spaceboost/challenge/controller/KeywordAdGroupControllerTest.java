package com.spaceboost.challenge.controller;

import com.spaceboost.challenge.BaseTest;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class KeywordAdGroupControllerTest extends BaseTest {

    @Test
    public void should_get_Campaign_with_most_cost_and_less_conversions() throws Exception {
        this.mockMvc.perform(
                get("/campaigns/mostCostLessConversions")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.keyword.id").value(27L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.keyword.campaignId").value(0L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.keyword.adGroupId").value(3L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.keyword.clicks").value(11))
                .andExpect(MockMvcResultMatchers.jsonPath("$.keyword.conversions").value(9))
                .andExpect(MockMvcResultMatchers.jsonPath("$.keyword.cost").value(47.44))

                .andExpect(MockMvcResultMatchers.jsonPath("$.adGroup.id").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$.adGroup.campaignId").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.adGroup.clicks").value(23))
                .andExpect(MockMvcResultMatchers.jsonPath("$.adGroup.conversions").value(59))
                .andExpect(MockMvcResultMatchers.jsonPath("$.adGroup.cost").value(463.42))
                .andReturn();
    }
}
