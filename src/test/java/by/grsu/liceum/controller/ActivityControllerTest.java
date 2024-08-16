package by.grsu.liceum.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import by.grsu.liceum.dto.activity.ActivityCreationDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ActivityControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void findAllTest() throws Exception {
        this.mockMvc.perform(get("/api/v1/activities")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void findByIdTest() throws Exception {
        this.mockMvc.perform(get("/api/v1/activities/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void createActivityTest() throws Exception {
        ActivityCreationDto creationDto = ActivityCreationDto.builder()
                .name("Smth")
                .activityTypeId(1)
                .countOfMembers(2)
                .description("activity test description")
                .build();

        this.mockMvc.perform(post("/api/v1/activities")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(creationDto)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteByIdTest() throws Exception {
        this.mockMvc.perform(delete("/api/v1/activities/1"))
                .andExpect(status().isOk());
    }
}
