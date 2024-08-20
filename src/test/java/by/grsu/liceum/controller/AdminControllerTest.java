package by.grsu.liceum.controller;

import by.grsu.liceum.dto.admin.RatingDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AdminControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Value("${controller.admin.url}")
    private String URL;

    @Test
    void addRatingTest() throws Exception {
        RatingDto ratingDto = RatingDto.builder()
                .accountId(1)
                .value(12)
                .build();

        this.mockMvc.perform(post(this.URL + "/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ratingDto)))
                .andExpect(status().isOk());
    }

    @Test
    void getRatingTest() throws Exception {
        RatingDto ratingDto = RatingDto.builder()
                .accountId(1)
                .value(12)
                .build();

        this.mockMvc.perform(post(this.URL + "/get")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ratingDto)))
                .andExpect(status().isOk());
    }
}
