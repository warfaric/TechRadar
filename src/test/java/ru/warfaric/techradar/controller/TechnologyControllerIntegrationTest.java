package ru.warfaric.techradar.controller;

import jakarta.transaction.Transactional;
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
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import ru.warfaric.techradar.TestDataUtil;
import ru.warfaric.techradar.entity.TechnologyEntity;
import ru.warfaric.techradar.entity.dto.TechnologyDto;
import ru.warfaric.techradar.service.TechnologyService;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
@Transactional
public class TechnologyControllerIntegrationTest {

    private TechnologyService technologyService;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;


    @Autowired
    public TechnologyControllerIntegrationTest(MockMvc mockMvc, TechnologyService technologyService) {
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
        this.technologyService = technologyService;
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
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatCreateTechnologySuccessfullyReturnsHttpStatusOk() throws Exception {
        TechnologyEntity testTechnologyA = TestDataUtil.createTestTechnology();
        testTechnologyA.setId(null);
        String technologyJson = objectMapper.writeValueAsString(testTechnologyA);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/technology")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(technologyJson)
        ).andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void testThatListTechnologiesReturnsHttp200() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/technology")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatListTechnologiesReturnsListOfTechnologies() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/technology")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].name").value("Terraform")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].ring").value("Test")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].section").value("DevOps")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].category").value("Software")
        );
    }

    @Test
    public void testThatGetTechnologiesReturnsHttp200WhenTechnologyExist() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/technology/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    public void testThatPartialUpdateTechnologiesReturnsHttp200() throws Exception {
        TechnologyEntity testTechnologyA = TestDataUtil.createTestTechnology();
        TechnologyEntity savedTechnology = technologyService.save(testTechnologyA);

        TechnologyDto testTechnologyDto = TestDataUtil.createTestDtoTechnology();
        testTechnologyDto.setName("UPDATED");
        String technologyDtoJson = objectMapper.writeValueAsString(testTechnologyDto);

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/api/technology/" + savedTechnology.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(technologyDtoJson)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatDeleteTechnologyReturnsHttp200() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/technology/999")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
