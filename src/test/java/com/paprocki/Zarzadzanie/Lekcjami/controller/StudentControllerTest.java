package com.paprocki.Zarzadzanie.Lekcjami.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paprocki.Zarzadzanie.Lekcjami.dto.StudentDTO;
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
class StudentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("Should correctly get single student")
    void shouldGetSingleStudent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/students/mirek@gmail.com"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Mirosław Kowalski"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("mirek@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.teacher").value("Michał Leja"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rate").value(200));
    }

    @Test
    @DisplayName("Should get not found when try to get user which is not in the db")
    void shouldNotGetSingleStudent() throws Exception {
        String email = "mirek@gmail.commmmm";
        mockMvc.perform(MockMvcRequestBuilders.get("/students/" + email))
                .andExpect(MockMvcResultMatchers.status().is(404))
                .andExpect(MockMvcResultMatchers.content().string("Brak studenta o danym emailu " + email));

    }

    @Test
    void shouldCorrectlyAddStudent() throws Exception {
        StudentDTO studentDTOToAdd = new StudentDTO("Janek", "jano@gmail.com", "Nauczyciel teahcer", 200);

        mockMvc.perform(MockMvcRequestBuilders.post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentDTOToAdd)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void shouldNotCorrectlyAddStudent() throws Exception {
        StudentDTO studentDTOToAdd = new StudentDTO("Janek", "jano2@gmail.com", "Nauczyciel teahcer", 200);

        mockMvc.perform(MockMvcRequestBuilders.post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentDTOToAdd)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentDTOToAdd)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Konto z takim email już istnieje"));
    }


    @Test
    void shouldUpdateStudent() throws Exception {
        StudentDTO studentDTOToAdd = new StudentDTO("Adam", "adam@gmail.com", "Nauczyciel teahcer", 200);
        mockMvc.perform(MockMvcRequestBuilders.post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentDTOToAdd)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        StudentDTO studentDTOToUpdate = new StudentDTO("Mikolaj", "adam@gmail.com", "Fajny teahcer", 300);
        mockMvc.perform(MockMvcRequestBuilders.put("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentDTOToUpdate)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Mikolaj"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("adam@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.teacher").value("Fajny teahcer"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rate").value(300));


    }

    @Test
    void shouldUpdateStudentPartially() throws Exception {
        StudentDTO studentDTOToAdd = new StudentDTO("Adam", "adamm@gmail.com", "Nauczyciel teahcer", 200);
        mockMvc.perform(MockMvcRequestBuilders.post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentDTOToAdd)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        StudentDTO studentDTOToUpdate = new StudentDTO("Bartek", "adamm@gmail.com", null, null);
        mockMvc.perform(MockMvcRequestBuilders.patch("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentDTOToUpdate)))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Bartek"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(studentDTOToUpdate.getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.teacher").value("Nauczyciel teahcer"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rate").value(200));
    }
}