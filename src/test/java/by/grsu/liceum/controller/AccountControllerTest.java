package by.grsu.liceum.controller;

import by.grsu.liceum.dto.account.AccountCreationDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Value("${controller.account.url}")
    private String URL;

    @Test
    void findByIdTest() throws Exception {
        this.mockMvc.perform(get(this.URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void findAllTest() throws Exception {
        this.mockMvc.perform(get(this.URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void saveTest() throws Exception {
        AccountCreationDto creationDto = AccountCreationDto.builder()
                .firstName("Даниил")
                .lastName("Савко")
                .fatherName("Андреевич")
                .phoneNumber("+375295252525")
                .yearOfStartOfStudies(2022)
                .build();

        this.mockMvc.perform(post(this.URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(creationDto)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteById() throws Exception {
        this.mockMvc.perform(delete(this.URL + "/1"))
                .andExpect(status().isOk());
    }
}
