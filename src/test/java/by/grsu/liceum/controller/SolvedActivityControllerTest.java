package by.grsu.liceum.controller;

import by.grsu.liceum.dto.solved_activity.SolveActivityRequest;
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
public class SolvedActivityControllerTest {
    private final String BAZE_PATH = "/api/v1/institutions/f287d9ff-728e-4a8f-ad79-8985fe55b50b/activities/solved";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @WithMockUser(roles = "SALE_UNIT")
    void testFindAllByInstitutionId() throws Exception {
        mockMvc.perform(get(BAZE_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void testFindAllByAccountId() throws Exception {
        mockMvc.perform(get(BAZE_PATH + "/accounts/58f82d24-ac0a-4508-a56d-f1fc0eee09fc")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "SALE_UNIT")
    void testFindAllByActivityId() throws Exception {
        mockMvc.perform(get(BAZE_PATH + "/activities/c6e9a9b5-1fa1-4d8b-bf26-78c8136c883f")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void testFindById() throws Exception {
        mockMvc.perform(get(BAZE_PATH + "/a69215a6-51ad-4797-89f3-2115f31340b9")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void testSolveActivity() throws Exception {
        SolveActivityRequest solveActivityRequest = SolveActivityRequest.builder()
                .accountId(UUID.fromString("58f82d24-ac0a-4508-a56d-f1fc0eee09fc"))
                .activityId(UUID.fromString("c6e9a9b5-1fa1-4d8b-bf26-78c8136c883f"))
                .code("15ABSDB52KJ5NJSA")
                .build();

        System.out.println(mockMvc.perform(post(BAZE_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(solveActivityRequest)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString());
    }

    @Test
    @WithMockUser(roles = "HEAD_TEACHER")
    void testDeleteById() throws Exception {
        mockMvc.perform(delete(BAZE_PATH + "/2f559133-e3c3-4c2a-bbf3-894ebbd73832"))
                .andExpect(status().isOk());
    }
}
