package by.grsu.liceum.controller;

import by.grsu.liceum.dto.account.admin.RatingDto;
import by.grsu.liceum.dto.image.ImageCreationDto;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AdminControllerTest {
    private final String BAZE_PATH = "/api/v1/institutions/f287d9ff-728e-4a8f-ad79-8985fe55b50b/admins";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @WithMockUser(roles = "ADMIN")
    void testAddRating() throws Exception {
        RatingDto dto = RatingDto.builder()
                .value(15)
                .accountId(UUID.fromString("58f82d24-ac0a-4508-a56d-f1fc0eee09fc"))
                .build();

        System.out.println(mockMvc.perform(post(BAZE_PATH + "/push")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk()) //todo repos -> accpimt
                .andReturn()
                .getResponse()
                .getContentAsString());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testGetRating() throws Exception{
        RatingDto dto = RatingDto.builder()
                .value(15)
                .accountId(UUID.fromString("58f82d24-ac0a-4508-a56d-f1fc0eee09fc"))
                .build();

        System.out.println(mockMvc.perform(post(BAZE_PATH + "/take")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testSetImage() throws Exception {
        ImageCreationDto creationDto = ImageCreationDto.builder()
                .objectName("image.png")
                .bucketName("bucketName")
                .build();

        System.out.println(mockMvc.perform(put(BAZE_PATH + "/6df0875f-05a8-44a2-b742-17d718058fb4/images")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(creationDto)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString());
    }
}
