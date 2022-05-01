package com.paprocki.Zarzadzanie.Lekcjami.controller;

import com.paprocki.Zarzadzanie.Lekcjami.model.Lesson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/lessons")
public class LessonController {

    private List<Lesson> lessons = new ArrayList<>();

    @PostConstruct
    public void init() {
        lessons.add(new Lesson(1,LocalDate.of(2020, 1, 8), "Eryk Dobaj", "Taduesz Paprocki", "Java"));
        lessons.add(new Lesson(2,LocalDate.of(2021, 3, 8), "Robert Maklowicz", "Taduesz Paprocki", "Gotowanie"));
        lessons.add(new Lesson(3,LocalDate.of(2001, 3, 18), "Dawid Fazowski", "Taduesz Paprocki", "Autostop"));
    }

    @GetMapping
    public ResponseEntity getAllLessons() {
        return new ResponseEntity(lessons, HttpStatus.OK);
    }

    @GetMapping("/{lessonId}")
    public ResponseEntity getSingleLesson(@PathVariable long lessonId) {
        return lessons.stream()
                .filter(lesson -> lesson.getLessonId() == lessonId)
                .findFirst()// czy moze byc tez findAny
                .map(lesson -> new ResponseEntity(lesson, HttpStatus.OK))
                .orElse(new ResponseEntity("Brak lekcji o podanym id " + lessonId, HttpStatus.NOT_FOUND));
//                .orElseGet(() -> new ResponseEntity("Brak lekcji o podanym id " + lessonId, HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity addLesson(@RequestBody Lesson lesson) {
        if (lessons.stream().anyMatch(l -> l.getLessonId() == lesson.getLessonId())) {
            return new ResponseEntity("Lekcja o podanym id juz istnieje", HttpStatus.BAD_REQUEST);
        }
        lessons.add(lesson);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping("/{lessonId}")
    public ResponseEntity deleteLesson(@PathVariable long lessonId) {
        if (!lessons.stream().anyMatch(l -> l.getLessonId() == lessonId)) {
            return new ResponseEntity("Lekcja o podanym id nie istnieje", HttpStatus.BAD_REQUEST);
        }
        lessons.remove(lessonId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity editLesson(@RequestBody Lesson updatedLesson) {
        if (lessons.stream().noneMatch(les -> les.getLessonId() == updatedLesson.getLessonId())) {
           return new ResponseEntity("Lekcja o podanym id juz istnieje", HttpStatus.BAD_REQUEST);
        }
        Lesson newLesson = lessons.stream().filter(l -> l.getLessonId() == updatedLesson.getLessonId()).findAny().get();
        newLesson.setLessonId(updatedLesson.getLessonId());
        newLesson.setDate(updatedLesson.getDate());
        newLesson.setStudentName(updatedLesson.getStudentName());
        newLesson.setTeacherName(updatedLesson.getTeacherName());
        newLesson.setTopic(updatedLesson.getTopic());
        return new ResponseEntity(newLesson, HttpStatus.OK);
    }


}
