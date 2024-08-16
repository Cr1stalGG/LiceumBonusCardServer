package by.grsu.liceum.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CardControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void findByIdTest() throws Exception {
        this.mockMvc.perform(get("/api/v1/cards/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void findAllTest() throws Exception {
        this.mockMvc.perform(get("/api/v1/cards")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteByIdTest() throws Exception {
        this.mockMvc.perform(delete("/api/v1/cards/1"))
                .andExpect(status().isOk());
    }
}
