package by.grsu.liceum.controller;

import by.grsu.liceum.dto.account.admin.RatingDto;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class HeadTeacherControllerTest {
    private final String BAZE_PATH = "/api/v1/institutions/f287d9ff-728e-4a8f-ad79-8985fe55b50b/head";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @WithMockUser(roles = "HEAD_TEACHER")
    void testFindGroupsOfInstitution() throws Exception {
        mockMvc.perform(get(BAZE_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "HEAD_TEACHER")
    void testAddRating() throws Exception{
        RatingDto ratingDto = RatingDto.builder()
                .accountId(UUID.fromString("58f82d24-ac0a-4508-a56d-f1fc0eee09fc"))
                .value(12)
                .build();

        System.out.println(mockMvc.perform(post(BAZE_PATH + "/push")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ratingDto)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString());
    }

    @Test
    @WithMockUser(roles = "HEAD_TEACHER")
    void testGetRating() throws Exception{
        RatingDto ratingDto = RatingDto.builder()
                .accountId(UUID.fromString("58f82d24-ac0a-4508-a56d-f1fc0eee09fc"))
                .value(12)
                .build();

        System.out.println(mockMvc.perform(post(BAZE_PATH + "/take")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ratingDto)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString());
    }
}
