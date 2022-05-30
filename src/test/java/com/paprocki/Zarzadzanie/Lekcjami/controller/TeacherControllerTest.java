package com.paprocki.Zarzadzanie.Lekcjami.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paprocki.Zarzadzanie.Lekcjami.model.Teacher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class TeacherControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("Should correctly get single teacher")
    void shouldGetSingleTeacher() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/teachers/arkadiusz.domanski@gmail.com"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Arkadiusz Domański"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("arkadiusz.domanski@gmail.com"));
    }

    @Test
    @DisplayName("Should get not found when try to get teacher which is not in the db")
    void shouldNotGetSingleTeacher() throws Exception {
        String email = "aaaarkadiusz.domanski@gmail.com";
        mockMvc.perform(MockMvcRequestBuilders.get("/teachers/" + email))
                .andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().string("Brak nauczyciela o danym emailu " + email));
    }

    @Test
    @DisplayName("Should correctly add teacher")
    void shouldCorrectlyAddTeacher() throws Exception {
        Teacher teacherToAdd = new Teacher("Batłomiej", "bartek@gmail.com");

        mockMvc.perform(MockMvcRequestBuilders.post("/teachers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(teacherToAdd)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @DisplayName("Should correctly not add teacher")
    void shouldNotCorrectlyAddTeacher() throws Exception {
        Teacher teacherToAdd = new Teacher("Arkadiusz Domański", "arkadiusz.domanski@gmail.com");

        mockMvc.perform(MockMvcRequestBuilders.post("/teachers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(teacherToAdd)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.post("/teachers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(teacherToAdd)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Konto z takim email już istnieje"));
    }

    @Test
    @DisplayName("Should correctly update teacher")
    void shouldUpdateTeacher() throws Exception {
        Teacher teacherToAdd = new Teacher("Arkadiuszz Domański", "arkadiuszz.domanski@gmail.com");
        mockMvc.perform(MockMvcRequestBuilders.post("/teachers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(teacherToAdd)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        Teacher teacherToUpdate = new Teacher("Arkadiuszz Domański", "arkadiuszz.domanski@gmail.com");
        mockMvc.perform(MockMvcRequestBuilders.put("/teachers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(teacherToUpdate)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("arkadiuszz.domanski@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Arkadiuszz Domański"));
    }

    @Test
    @DisplayName("Should correctly update teacher partially")
    void shouldPartiallyUpdateTeacher() throws Exception {
        Teacher teacherToAdd = new Teacher("Arkadiuszz Domański", "arkadiuszz.domanski@gmail.com");
        mockMvc.perform(MockMvcRequestBuilders.post("/teachers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(teacherToAdd)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        Teacher teacherToUpdate = new Teacher("Arkadiuszz Domański", "arkadiuszz.domanski@gmail.com");
        mockMvc.perform(MockMvcRequestBuilders.patch("/teachers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(teacherToUpdate)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("arkadiuszz.domanski@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Arkadiuszz Domański"));
    }
    }


















