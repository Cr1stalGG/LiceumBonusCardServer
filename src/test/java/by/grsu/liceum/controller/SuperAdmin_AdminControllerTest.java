package by.grsu.liceum.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class SuperAdmin_AdminControllerTest {
    private final String BAZE_PATH = "/api/v1/root/admins";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @WithMockUser(roles = "SUPER_ADMIN")
    void testFindAllAdmins() throws Exception {
        mockMvc.perform(get(BAZE_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "SUPER_ADMIN")
    void testFindAllAdminsByCity() throws Exception {
        mockMvc.perform(get(BAZE_PATH + "/city/Grodno")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "SUPER_ADMIN")
    void testFindAdminById() throws Exception {
        mockMvc.perform(get(BAZE_PATH + "/6df0875f-05a8-44a2-b742-17d718058fb4")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "SUPER_ADMIN")
    void testCreateAdmin() throws Exception {
        System.out.println(mockMvc.perform(post(BAZE_PATH + "/0704e21b-c320-4819-b226-31bcec7f9854")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString());
    }

    @Test
    @WithMockUser(roles = "SUPER_ADMIN")
    void testRegeneratePassword() throws Exception {
        System.out.println(mockMvc.perform(put(BAZE_PATH + "/f287d9ff-728e-4a8f-ad79-8985fe55b50b/regenerate/password/6df0875f-05a8-44a2-b742-17d718058fb4")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString());
    }

    @Test
    @WithMockUser(roles = "SUPER_ADMIN")
    void testDeleteById() throws Exception {
        mockMvc.perform(delete(BAZE_PATH + "/6df0875f-05a8-44a2-b742-17d718058fb4"))
                .andExpect(status().isOk());
    }
}
