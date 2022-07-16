package com.paprocki.Zarzadzanie.Lekcjami.controller;

import com.paprocki.Zarzadzanie.Lekcjami.dto.LessonDTO;
import com.paprocki.Zarzadzanie.Lekcjami.enitites.LessonEntity;
import com.paprocki.Zarzadzanie.Lekcjami.services.LessonService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lessons")
@AllArgsConstructor
public class LessonController {

    private List<LessonDTO> lessonDTOS = new ArrayList<>();
    private final ModelMapper modelMapper;
    private final LessonService lessonService;

    @GetMapping
    public ResponseEntity getAllLessons() {
        return new ResponseEntity(lessonService.getAllLessons(), HttpStatus.OK);
    }

    @GetMapping("/{lessonId}")
    public ResponseEntity getSingleLesson(@PathVariable long lessonId) {
        return lessonService.getSingleLesson(lessonId).map(lessonDTO -> new ResponseEntity(lessonDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity("Brak lekcji o podanym id " + lessonId, HttpStatus.BAD_REQUEST));
    }

    @PostMapping
    public boolean addLesson(@RequestBody LessonDTO lessonDTO) {
        if (lessonService.getSingleLesson(lessonDTO.getLessonId()).isEmpty()) {
            lessonService.addLesson(lessonDTO);
            return true;
        }
        return false;
    }

//    @DeleteMapping("/{lessonId}")
//    public ResponseEntity deleteLesson(@PathVariable long lessonId) {
//        if (!lessonDTOS.stream().anyMatch(l -> l.getLessonId() == lessonId)) {
//            return new ResponseEntity("Lekcja o podanym id nie istnieje", HttpStatus.BAD_REQUEST);
//        }
//        lessonDTOS.remove(lessonId);
//        return new ResponseEntity(HttpStatus.NO_CONTENT);
//        if(lessonService.)
//    }

    @PutMapping
    public ResponseEntity editLesson(@RequestBody LessonDTO updatedLessonDTO) {
        return lessonService.updateLesson(updatedLessonDTO)
                .map(l -> new ResponseEntity(l, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity("Brak lekcji o danym id " + updatedLessonDTO.getLessonId(), HttpStatus.NOT_FOUND));
    }

    @PatchMapping
    public ResponseEntity editLessonPartially(@RequestBody LessonDTO updatedLessonDTO) {
        return lessonService.updateLessonPartially(updatedLessonDTO)
                .map(l -> new ResponseEntity(l, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity("Brak lekcji o danym id " + updatedLessonDTO.getLessonId(), HttpStatus.NOT_FOUND));
    }
}
