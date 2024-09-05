package by.grsu.liceum.controller;

import by.grsu.liceum.dto.activity.ActivityCreationDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ActivityControllerTest {
    private final String BAZE_PATH = "/api/v1/institutions/f287d9ff-728e-4a8f-ad79-8985fe55b50b/activities";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @WithMockUser(roles = "USER")
    void testFindAll() throws Exception {
        mockMvc.perform(get(BAZE_PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void testFindById() throws Exception {
        mockMvc.perform(get(BAZE_PATH + "/2aa1db1e-964f-476d-8c2f-0500f0f5e8f3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "HEAD_TEACHER")
    void testCreateActivity() throws Exception {
        ActivityCreationDto creationDto = ActivityCreationDto.builder()
                .name("ACTIVITY")
                .description("some description for test activity")
                .countOfMembers(15)
                .activityTypeId(UUID.fromString("e7d87e44-6337-4ee8-9bc7-cb252696a49e"))
                .build();

        System.out.println(mockMvc.perform(post(BAZE_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(creationDto)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testDeleteById() throws Exception {
        mockMvc.perform(delete(BAZE_PATH + "/2aa1db1e-964f-476d-8c2f-0500f0f5e8f3"))
                .andExpect(status().isOk());
    }
}
