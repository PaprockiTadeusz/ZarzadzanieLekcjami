package com.paprocki.Zarzadzanie.Lekcjami.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paprocki.Zarzadzanie.Lekcjami.dto.StudentDTO;
import com.paprocki.Zarzadzanie.Lekcjami.enitites.LessonEntity;
import com.paprocki.Zarzadzanie.Lekcjami.enitites.TeacherEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.ArrayList;

@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
//    static LessonEntity lesson1 = new LessonEntity(LocalDateTime.of(2020,12,1,12,0),)
//    static TeacherEntity teacher = new TeacherEntity("Bartek Paprocki", "bartek.paprocki@gmail.com", )

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
        // given
        String email = "mirek@gmail.commmmm";

        // when
        // then
        // polaczenie when i then daje expect

        // expect
        mockMvc.perform(MockMvcRequestBuilders.get("/students/" + email))
                .andExpect(MockMvcResultMatchers.status().is(404))
                .andExpect(MockMvcResultMatchers.content().string("Brak studenta o danym emailu " + email));

    }

    @Test
    void shouldCorrectlyAddStudent() throws Exception {
        StudentDTO studentDTOToAdd = new StudentDTO("Janek", "jano@gmail.com", );

        mockMvc.perform(MockMvcRequestBuilders.post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentDTOToAdd)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void shouldNotCorrectlyAddStudent() throws Exception {
        StudentDTO studentDTOToAdd = new StudentDTO();
//        StudentDTO studentDTOToAdd = new StudentDTO("Janek", "jano2@gmail.com", "Nauczyciel teahcer", 200);

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
        // given
        TeacherEntity teacher = TeacherEntity.builder()
                .id(1)
                .email("test@o2.pl")
                .name("Kazik")
                .build();
        // teacherrepository(teacher).save...
        StudentDTO studentDTOToAdd = new StudentDTO("Adam", "adam@gmail.com", teacher, 200, new ArrayList<>());
        mockMvc.perform(MockMvcRequestBuilders.post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentDTOToAdd)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        StudentDTO studentDTOToUpdate = new StudentDTO("Mikolaj", "adam@gmail.com",);//"Mikolaj", "adam@gmail.com", "Fajny teahcer", 300
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