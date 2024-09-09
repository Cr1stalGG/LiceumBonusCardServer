package by.grsu.liceum.controller;

import by.grsu.liceum.dto.account.AccountCreationDto;
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

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AccountControllerTest {
    private final String BAZE_PATH = "/api/v1/institutions/f287d9ff-728e-4a8f-ad79-8985fe55b50b/accounts";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "login", password = "password", roles = "SUPER_ADMIN")
    void testFindById() throws Exception {
        mockMvc.perform(get(BAZE_PATH + "/3234088c-210a-4f13-9a7e-e6900a1e2036")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(username = "login", password = "password", roles = "SUPER_ADMIN")
    void testFindAll() throws Exception {
        mockMvc.perform(get(BAZE_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "login", password = "password", roles = "SUPER_ADMIN")
    void testCreateAccountWithRole() throws  Exception {
        AccountCreationDto accountCreationDto = AccountCreationDto.builder()
                .firstName("Smth")
                .lastName("Smth")
                .fatherName("Smth")
                .grade(11)
                .phoneNumber("+375291919191")
                .roleNames(List.of("ROLE_USER"))
                .build();

        System.out.println(mockMvc.perform(post(BAZE_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(accountCreationDto)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString());
    }

    @Test
    @WithMockUser(username = "login", password = "password", roles = "SUPER_ADMIN")
    void testCreateAccountWithRoleValidation() throws  Exception {
        AccountCreationDto accountCreationDto = AccountCreationDto.builder()
                .lastName("Smth")
                .fatherName("Smth")
                .grade(11)
                .phoneNumber("+375291919191")
                .roleNames(List.of("ROLE_USER"))
                .build();

        System.out.println(mockMvc.perform(post(BAZE_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accountCreationDto)))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse()
                .getContentAsString());
    }

    @Test
    @WithMockUser(username = "login", password = "password", roles = "SUPER_ADMIN")
    void testRegeneratePassword() throws Exception{
        System.out.println(mockMvc.perform(put(BAZE_PATH + "/3234088c-210a-4f13-9a7e-e6900a1e2036/regenerate/password")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString());
    }

    @Test
    @WithMockUser(username = "login", password = "password", roles = "SUPER_ADMIN")
    void testUpdateImage() throws Exception {
        ImageCreationDto creationDto = ImageCreationDto.builder()
                .bucketName("bucketName")
                .objectName("objectName.png")
                .build();

        System.out.println(mockMvc.perform(put(BAZE_PATH + "/3234088c-210a-4f13-9a7e-e6900a1e2036/images")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(creationDto)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString());
    }

    @Test
    @WithMockUser(username = "login", password = "password", roles = "SUPER_ADMIN")
    void testDeleteAccountById() throws Exception{
        mockMvc.perform(delete(BAZE_PATH + "/3234088c-210a-4f13-9a7e-e6900a1e2036"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "SUPER_ADMIN")
    void testAutoDeleteAccounts() throws Exception {
        mockMvc.perform(get(BAZE_PATH + "/5cbb7d35-813f-4f6b-a6e3-b13d16affed8")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
