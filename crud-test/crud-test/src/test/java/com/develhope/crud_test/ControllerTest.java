package com.develhope.crud_test;

import com.develhope.crud_test.entities.StudentEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Order(1)
    public void createUser() throws Exception {

        StudentEntity u = new StudentEntity();
        u.setId(1L);
        u.setName("Mario");
        u.setSurname("Rossi");
        u.setIsWorking(true);

        String userJson = objectMapper.writeValueAsString(u);

        this.mockMvc.perform(post("/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson)).andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @Order(2)
    public void testGetAllStudents() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/v1/user/list"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        List<StudentEntity> userFromResponse = objectMapper.readValue(result.getResponse().getContentAsString(), List.class);
        assert(!userFromResponse.isEmpty());
    }

    @Test
    @Order(3)
    public void testGetStudentById() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/v1/user/"+1))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        StudentEntity userFromResponse = objectMapper.readValue(result.getResponse().getContentAsString(), StudentEntity.class);
        assert (userFromResponse.getName().equals("Mario"));
    }
    @Test
    @Order(4)
    public void testSwitchWorking() throws Exception {
        MvcResult result = this.mockMvc.perform(patch("/v1/user/workingSwitch/"+1+"?working=false"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        StudentEntity userFromResponse = objectMapper.readValue(result.getResponse().getContentAsString(), StudentEntity.class);
        assert (userFromResponse.getisWorking().equals(false));

    }

    @Test
    @Order(5)
    public void testChangeInfo() throws Exception {
        StudentEntity u = new StudentEntity();
        u.setName("Bilbo");
        u.setSurname("Baggins");
        String userJson = objectMapper.writeValueAsString(u);
        MvcResult result = this.mockMvc.perform(put("/v1/user/changeInfo/"+1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson)).andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        StudentEntity userFromResponse = objectMapper.readValue(result.getResponse().getContentAsString(), StudentEntity.class);
        assert (userFromResponse.getName().equals("Bilbo"));
        assert (userFromResponse.getSurname().equals("Baggins"));
    }

    @Test
    @Order(6)
    public void testDeleteStudents() throws Exception {
        this.mockMvc.perform(delete("/v1/user/"+1))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
