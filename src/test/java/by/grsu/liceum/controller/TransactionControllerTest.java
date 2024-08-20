package by.grsu.liceum.controller;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class TransactionControllerTest {
    @Autowired
    private MockMvc mockMvc;
    
    @Value("${controller.transaction.url}")
    private String URL;

    @Test
    void findAllTest() throws Exception {
        this.mockMvc.perform(get(this.URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void findAllByCardIdTest() throws Exception {
        this.mockMvc.perform(get(this.URL + "/card/1")
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
    void deleteByIdTest() throws Exception {
        this.mockMvc.perform(delete(this.URL + "/1"))
                .andExpect(status().isOk());
    }
}
