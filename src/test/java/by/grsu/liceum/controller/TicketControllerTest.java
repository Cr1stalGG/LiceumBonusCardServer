package by.grsu.liceum.controller;

import by.grsu.liceum.dto.ticket.SetTicketDto;
import by.grsu.liceum.dto.ticket.TicketReadCodeDto;
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
public class TicketControllerTest {
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Value("${controller.ticket.url}")
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
    void setTicketToTheAccountTest() throws Exception {
        SetTicketDto ticketDto = SetTicketDto.builder()
                .accountId(1)
                .bonusId(1)
                .build();
        
        this.mockMvc.perform(post(this.URL + "/set")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(ticketDto)))
                .andExpect(status().isOk());
        
    }
    
    @Test
    void rollTicketBackTest() throws Exception {
        this.mockMvc.perform(post(this.URL + "/1"))
                .andExpect(status().isOk());
    }
    
    @Test
    void readCodeTest() throws Exception {
        TicketReadCodeDto readCodeDto = TicketReadCodeDto.builder()
                .uuid(1)
                .code("#asdasd")
                .build();
        
        this.mockMvc.perform(post(this.URL + "/code")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(readCodeDto)))
                .andExpect(status().isOk());
    }
    
    @Test
    void deleteByIdTest() throws Exception {
        this.mockMvc.perform(delete(this.URL + "/1"))
                .andExpect(status().isOk());
    }
}
