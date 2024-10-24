package ru.warfaric.techradar.controller;

import lombok.Builder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import ru.warfaric.techradar.TestDataUtil;
import ru.warfaric.techradar.entity.TechnologyEntity;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class TechnologyControllerIntegrationTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Autowired
    public TechnologyControllerIntegrationTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testThatCreateTechnologySuccessfullyReturnsHttp201Created() throws Exception {
        TechnologyEntity testTechnologyA = TestDataUtil.createTestTechnology();
        testTechnologyA.setId(null);
        String technologyJson = objectMapper.writeValueAsString(testTechnologyA);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/technology")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(technologyJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }
}
