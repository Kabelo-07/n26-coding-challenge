package com.n26.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.n26.statistics.model.StatisticsModel;
import com.n26.statistics.service.StatisticsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class StatisticsResourceTest {

    private StatisticsResource statisticsResource;

    @Mock private StatisticsService statisticsService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {
        statisticsResource = new StatisticsResource(statisticsService);
        mockMvc = MockMvcBuilders.standaloneSetup(statisticsResource).build();
    }

    @Test
    public void test_getStatistics() throws Exception {
        StatisticsModel model = StatisticsModel.defaultInstance();
        model.setAvg(BigDecimal.valueOf(400));
        model.setMax(BigDecimal.valueOf(5000));
        model.setCount(4);
        when(statisticsService.getStatistics()).thenReturn(model);

        mockMvc.perform(MockMvcRequestBuilders.get("/statistics"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(model)));
    }
}
