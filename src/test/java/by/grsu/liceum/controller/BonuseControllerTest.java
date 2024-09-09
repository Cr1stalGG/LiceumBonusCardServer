package by.grsu.liceum.controller;

import by.grsu.liceum.dto.bonus.BonusBuyDto;
import by.grsu.liceum.dto.bonus.BonusCreationDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class BonuseControllerTest {
    private final String BAZE_PATH = "/api/v1/institutions/f287d9ff-728e-4a8f-ad79-8985fe55b50b/bonuses";

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
        mockMvc.perform(get(BAZE_PATH + "/5cbb7d35-813f-4f6b-a6e3-b13d16affed8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "HEAD_TEACHER")
    void testCreateBonus() throws Exception {
        BonusCreationDto creationDto = BonusCreationDto.builder()
                .name("Bonuss")
                .description("descr")
                .price(123)
                .count(22)
                .timeOfEnd(new Date(System.currentTimeMillis() + 15000))
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
    @WithMockUser(roles = "USER")
    void testBuyBonus() throws Exception {
        BonusBuyDto buyDto = BonusBuyDto.builder()
                .bonusId(UUID.fromString("5cbb7d35-813f-4f6b-a6e3-b13d16affed8"))
                .accountId(UUID.fromString("58f82d24-ac0a-4508-a56d-f1fc0eee09fc"))
                .build();

        System.out.println(mockMvc.perform(post(BAZE_PATH + "/buy")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(buyDto)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString());
    }

    @Test
    @WithMockUser(roles = "HEAD_TEACHER")
    void testDeleteById() throws Exception {
        mockMvc.perform(delete(BAZE_PATH + "/5cbb7d35-813f-4f6b-a6e3-b13d16affed8"))
                .andExpect(status().isOk());
    }

}
