package by.grsu.liceum.controller;

import by.grsu.liceum.dto.group.AddMembersDto;
import by.grsu.liceum.dto.group.GroupCreationDto;
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
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class GroupControllerTest {
    private final String BAZE_PATH = "/api/v1/institutions/f287d9ff-728e-4a8f-ad79-8985fe55b50b/groups";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @WithMockUser(roles = "USER")
    void testFindGroupById() throws Exception {
        mockMvc.perform(get(BAZE_PATH + "/5c04c8bf-4653-4d94-89ef-181c401b5de1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "TEACHER")
    void testCreateNewGroup() throws Exception {
        GroupCreationDto creationDto = GroupCreationDto.builder()
                .name("TEST GROUP")
                .adminId(UUID.fromString("6df0875f-05a8-44a2-b742-17d718058fb4"))
                .membersId(List.of(UUID.fromString("58f82d24-ac0a-4508-a56d-f1fc0eee09fc")))
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
    @WithMockUser(roles = "TEACHER")
    void testAddMembersToTheGroup() throws Exception {
        AddMembersDto addMembersDto = AddMembersDto.builder()
                .groupId(UUID.fromString("5c04c8bf-4653-4d94-89ef-181c401b5de1"))
                .adminId(UUID.fromString("6df0875f-05a8-44a2-b742-17d718058fb4"))
                .membersId(List.of(UUID.fromString("58f82d24-ac0a-4508-a56d-f1fc0eee09fc")))
                .build();

        System.out.println(mockMvc.perform(post(BAZE_PATH + "/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addMembersDto)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString());

    }

    @Test
    @WithMockUser(roles = "TEACHER")
    void testDeleteGroupById() throws Exception {
        mockMvc.perform(delete(BAZE_PATH + "/5c04c8bf-4653-4d94-89ef-181c401b5de1"))
                .andExpect(status().isOk());
    }
}
