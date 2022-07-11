package com.paprocki.Zarzadzanie.Lekcjami.controller;

import com.paprocki.Zarzadzanie.Lekcjami.dto.LessonDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lessons")
public class LessonController {

    private List<LessonDTO> lessonDTOS = new ArrayList<>();

    @PostConstruct
    public void init() {
        lessonDTOS.add(new LessonDTO(1, LocalDateTime.of(2020, 1, 8, 1, 1), "Eryk Dobaj", "Taduesz Paprocki", "Java"));
        lessonDTOS.add(new LessonDTO(2, LocalDateTime.of(2021, 3, 8,2,3), "Robert Maklowicz", "Taduesz Paprocki", "Gotowanie"));
        lessonDTOS.add(new LessonDTO(3, LocalDateTime.of(2001, 3, 18,4,5), "Dawid Fazowski", "Taduesz Paprocki", "Autostop"));
    }

    @GetMapping
    public ResponseEntity getAllLessons() {
        return new ResponseEntity(lessonDTOS, HttpStatus.OK);
    }

    @GetMapping("/{lessonId}")
    public ResponseEntity getSingleLesson(@PathVariable long lessonId) {
        return lessonDTOS.stream()
                .filter(lessonDTO -> lessonDTO.getLessonId() == lessonId)
                .findFirst()
                .map(lessonDTO -> new ResponseEntity(lessonDTO, HttpStatus.OK))
                .orElse(new ResponseEntity("Brak lekcji o podanym id " + lessonId, HttpStatus.NOT_FOUND));
//                .orElseGet(() -> new ResponseEntity("Brak lekcji o podanym id " + lessonId, HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity addLesson(@RequestBody LessonDTO lessonDTO) {
        if (lessonDTOS.stream().anyMatch(l -> l.getLessonId() == lessonDTO.getLessonId())) {
            return new ResponseEntity("Lekcja o podanym id juz istnieje", HttpStatus.BAD_REQUEST);
        }
        lessonDTOS.add(lessonDTO);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping("/{lessonId}")
    public ResponseEntity deleteLesson(@PathVariable long lessonId) {
        if (!lessonDTOS.stream().anyMatch(l -> l.getLessonId() == lessonId)) {
            return new ResponseEntity("Lekcja o podanym id nie istnieje", HttpStatus.BAD_REQUEST);
        }
        lessonDTOS.remove(lessonId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity editLesson(@RequestBody LessonDTO updatedLessonDTO) {
        if (lessonDTOS.stream().noneMatch(les -> les.getLessonId() == updatedLessonDTO.getLessonId())) {
            return new ResponseEntity("Lekcja o podanym id juz istnieje", HttpStatus.BAD_REQUEST);
        }
        LessonDTO newLessonDTO = lessonDTOS.stream().filter(l -> l.getLessonId() == updatedLessonDTO.getLessonId()).findAny().get();
        newLessonDTO.setLessonId(updatedLessonDTO.getLessonId());
        newLessonDTO.setDate(updatedLessonDTO.getDate());
        newLessonDTO.setStudentName(updatedLessonDTO.getStudentName());
        newLessonDTO.setTeacherName(updatedLessonDTO.getTeacherName());
        newLessonDTO.setTopic(updatedLessonDTO.getTopic());
        return new ResponseEntity(newLessonDTO, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity editLessonPartially(@RequestBody LessonDTO updatedLessonDTO) {
        if (lessonDTOS.stream().noneMatch(les -> les.getLessonId() == updatedLessonDTO.getLessonId())) {
            return new ResponseEntity("Lekcja o podanym id juz istnieje", HttpStatus.BAD_REQUEST);
        }
        LessonDTO newLessonDTO = lessonDTOS.stream().filter(l -> l.getLessonId() == updatedLessonDTO.getLessonId()).findAny().get();
        Optional.ofNullable(updatedLessonDTO.getTopic()).ifPresent(newLessonDTO::setTopic);
        Optional.ofNullable(updatedLessonDTO.getDate()).ifPresent(newLessonDTO::setDate);
        Optional.ofNullable(updatedLessonDTO.getStudentName()).ifPresent(newLessonDTO::setStudentName);
        Optional.ofNullable(updatedLessonDTO.getTeacherName()).ifPresent(newLessonDTO::setTeacherName);
        return new ResponseEntity(newLessonDTO, HttpStatus.OK);
    }
}
