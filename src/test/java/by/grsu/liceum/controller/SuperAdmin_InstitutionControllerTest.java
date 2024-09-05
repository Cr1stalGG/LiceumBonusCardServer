package by.grsu.liceum.controller;

import by.grsu.liceum.dto.image.ImageCreationDto;
import by.grsu.liceum.dto.institution.InstitutionCreationDto;
import by.grsu.liceum.dto.institution.InstitutionUpdateDto;
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
public class SuperAdmin_InstitutionControllerTest {
    private final String BAZE_PATH = "/api/v1/root/institutions";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "login", password = "pass", roles = "SUPER_ADMIN")
    void testFindAllInstitutions() throws Exception {
        mockMvc.perform(get(BAZE_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "login", password = "pass", roles = "SUPER_ADMIN")
    void testFindAllInstitutionsByCity() throws Exception {
        mockMvc.perform(get(BAZE_PATH + "/city/Grodno")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "login", password = "pass", roles = "SUPER_ADMIN")
    void testFindAllInstitutionsByNameLike() throws Exception {
        mockMvc.perform(get(BAZE_PATH + "/name/ROOT")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "login", password = "pass", roles = "SUPER_ADMIN")
    void testFindInstitutionById() throws Exception {
        System.out.println(mockMvc.perform(get(BAZE_PATH + "/f287d9ff-728e-4a8f-ad79-8985fe55b50b")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString());
    }

    @Test
    @WithMockUser(username = "login", password = "pass", roles = "SUPER_ADMIN")
    void testCreateInstitution() throws Exception {
        InstitutionCreationDto creationDto = InstitutionCreationDto.builder()
                .name("YSSD")
                .city("MINSK")
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
    @WithMockUser(username = "login", password = "pass", roles = "SUPER_ADMIN")
    void testUpdateInstitution() throws Exception {
        InstitutionUpdateDto updateDto = InstitutionUpdateDto.builder()
                .city("MINSk")
                .build();

        mockMvc.perform(put(BAZE_PATH + "/f287d9ff-728e-4a8f-ad79-8985fe55b50b")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateDto)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "login", password = "pass", roles = "SUPER_ADMIN")
    void testSetImage() throws Exception {
        ImageCreationDto creationDto = ImageCreationDto.builder()
                .objectName("object.png")
                .bucketName("bucketName")
                .build();

        System.out.println(mockMvc.perform(put(BAZE_PATH + "/f287d9ff-728e-4a8f-ad79-8985fe55b50b/images")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(creationDto)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString());
    }

    @Test
    @WithMockUser(roles = "SUPER_ADMIN")
    void testDeleteInstitutionById() throws Exception {
        mockMvc.perform(delete(BAZE_PATH + "/f287d9ff-728e-4a8f-ad79-8985fe55b50b"))
                .andExpect(status().isOk());
    }
}
