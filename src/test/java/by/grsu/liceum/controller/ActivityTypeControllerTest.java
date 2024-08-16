package by.grsu.liceum.controller;

import by.grsu.liceum.dto.activity_type.ActivityTypeCreationDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ActivityTypeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void findByIdTest() throws Exception {
        this.mockMvc.perform(get("/api/v1/activities/types/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void findAllTest() throws Exception {
        this.mockMvc.perform(get("/api/v1/activities/types")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void createActivityTypeTest() throws Exception {
        ActivityTypeCreationDto activityTypeCreationDto = ActivityTypeCreationDto.builder()
                .name("test")
                .cost(22)
                .build();

        this.mockMvc.perform(post("/api/v1/activities/types")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(activityTypeCreationDto)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteByIdTest() throws Exception {
        this.mockMvc.perform(delete("/api/v1/activities/types/1"))
                .andExpect(status().isOk());
    }
}
