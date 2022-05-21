package com.paprocki.Zarzadzanie.Lekcjami.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paprocki.Zarzadzanie.Lekcjami.model.Lesson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentLessonController {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("Should correctly get single lesson")
    void shouldGetLesson() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/lessons/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.topic").value("Java"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.studentName").value("Taduesz Paprocki"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.teacherName").value("Eryk Dobaj"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lessonId").value("1"));
    }


    @Test
    @DisplayName("Should get not found when try to get lesson")
    void shouldNotGetSingleLesson() throws Exception {
        int id = 70;
        mockMvc.perform(MockMvcRequestBuilders.get("/lessons/" + id))
                .andExpect(MockMvcResultMatchers.status().is(404))
                .andExpect(MockMvcResultMatchers.content().string("Brak lekcji o podanym id " + id));

    }
    @Test
    void shouldCorrectlyAddLesson() throws Exception {
        Lesson lessonToAdd = new Lesson(8, LocalDate.of(2020, 01, 8), "Erykkk Dobaj", "TTTaduesz Paprocki", "Spring");

        mockMvc.perform(MockMvcRequestBuilders.post("/lessons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(lessonToAdd)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @DisplayName("Should correctly delete lesson")
    void shouldDeleteLesson() throws Exception {
        int id = 1;
        mockMvc.perform(MockMvcRequestBuilders.delete("/lessons/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

}
