package by.grsu.liceum.controller;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class TransactionControllerTest {
    private final String BAZE_PATH = "/api/v1/institutions/f287d9ff-728e-4a8f-ad79-8985fe55b50b/transactions";

    @Autowired
    MockMvc mockMvc;

    @Test
    @WithMockUser(roles = "SALE_UNIT")
    void testFindAllByInstitution() throws Exception {
        mockMvc.perform(get(BAZE_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void testFindAllByCardId() throws Exception {
        System.out.println(mockMvc.perform(get(BAZE_PATH + "/card/a24740d4-b180-420e-9909-fea8ae4e7598")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString());
    }

    @Test
    @WithMockUser(roles = "USER")
    void testFindById() throws Exception {
        mockMvc.perform(get(BAZE_PATH + "/18ebea50-c232-46a8-bf6e-f49a477115bf")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "HEAD_TEACHER")
    void testDeleteById() throws Exception {
        mockMvc.perform(delete(BAZE_PATH + "/18ebea50-c232-46a8-bf6e-f49a477115bf"))
                .andExpect(status().isOk());
    }

}
