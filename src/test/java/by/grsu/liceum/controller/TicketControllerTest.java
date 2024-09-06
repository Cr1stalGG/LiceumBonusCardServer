package by.grsu.liceum.controller;

import by.grsu.liceum.dto.ticket.SetTicketDto;
import by.grsu.liceum.dto.ticket.TicketReadCodeDto;
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
public class TicketControllerTest {
    private final String BAZE_PATH = "/api/v1/institutions/f287d9ff-728e-4a8f-ad79-8985fe55b50b/tickets";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @WithMockUser(roles = "SALE_UNIT")
    void testFindAllByInstitution() throws Exception {
        mockMvc.perform(get(BAZE_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void testFindById() throws Exception {
        mockMvc.perform(get(BAZE_PATH + "/8532ee9e-63e5-4838-b313-71722287f3cb")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "TEACHER")
    void testSetTicketToTheAccount() throws Exception {
        SetTicketDto setTicketDto = SetTicketDto.builder()
                .accountId(UUID.fromString("58f82d24-ac0a-4508-a56d-f1fc0eee09fc"))
                .bonusId(UUID.fromString("5cbb7d35-813f-4f6b-a6e3-b13d16affed8"))
                .build();

        System.out.println(mockMvc.perform(post(BAZE_PATH + "/set")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(setTicketDto)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString());
    }

    @Test
    @WithMockUser(roles = "USER")
    void testRollTicketBack() throws Exception {
        mockMvc.perform(post(BAZE_PATH + "/8532ee9e-63e5-4838-b313-71722287f3cb"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "SALE_UNIT")
    void testReadCode() throws Exception {
        TicketReadCodeDto readCodeDto = TicketReadCodeDto.builder()
                .code("24wr5678asdf")
                .uuid(UUID.fromString("8532ee9e-63e5-4838-b313-71722287f3cb"))
                .build();

        mockMvc.perform(post(BAZE_PATH + "/code")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(readCodeDto)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "HEAD_TEACHER")
    void testDeleteById() throws Exception {
        mockMvc.perform(delete(BAZE_PATH + "/8532ee9e-63e5-4838-b313-71722287f3cb"))
                .andExpect(status().isOk());
    }
}
