package by.grsu.liceum.controller;

import by.grsu.liceum.dto.bonus.BonusCreationDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class BonusControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Value("${controller.bonus.url}")
    private String URL;

    @Test
    void findAllTest() throws Exception {
        this.mockMvc.perform(get(this.URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void findByIdTest() throws Exception {
        this.mockMvc.perform(get(this.URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void createBonusTest() throws Exception {
        BonusCreationDto bonusCreationDto = BonusCreationDto.builder()
                .name("test")
                .description("test")
                .count(1)
                .price(12)
                .timeOfEnd(new Date(System.currentTimeMillis()))
                .build();

        this.mockMvc.perform(post(this.URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bonusCreationDto)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteByIdTest() throws Exception {
        this.mockMvc.perform(delete(this.URL + "/1"))
                .andExpect(status().isOk());
    }
}
