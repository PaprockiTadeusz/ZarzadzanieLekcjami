package com.paprocki.Zarzadzanie.Lekcjami.controller;

import com.paprocki.Zarzadzanie.Lekcjami.model.Teacher;
import com.paprocki.Zarzadzanie.Lekcjami.services.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("/email")
    public ResponseEntity getAllTeachers() {
        return new ResponseEntity(teacherService.getAllTeachers(), HttpStatus.OK);
    }

    @GetMapping("/{email}")
    public ResponseEntity getOneTeacher(@PathVariable String email) {
        Optional<Teacher> newTeacher = teacherService.getSingleTeacher(email);
        return newTeacher.map(teacher -> new ResponseEntity(teacher, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity("Brak nauczyciela o danym emailu " + email, HttpStatus.BAD_REQUEST));
    }

    @PostMapping
    public ResponseEntity addTeacher(@RequestBody Teacher teacher) {
        if (teacherService.addNewTeacher(teacher)) {
            return new ResponseEntity<>(teacher, HttpStatus.CREATED);
        } else {
            return new ResponseEntity("Konto z takim emailem już istnieje", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping()
    public ResponseEntity editTeacher(@RequestBody Teacher updatedTeacher) {
        return teacherService.editTeacher(updatedTeacher)
                .map(t -> new ResponseEntity<>(t, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity("Brak nauczyciela o danym mailu " + updatedTeacher.getEmail(), HttpStatus.NOT_FOUND));
    }

    @PatchMapping
    public ResponseEntity editTeacherPartially(@RequestBody Teacher updatedTeacher) {
        return teacherService.editTeacherPartially(updatedTeacher)
                .map(t -> new ResponseEntity<>(t, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity("Brak nauczyciela o danym mailu " + updatedTeacher.getEmail(), HttpStatus.NOT_FOUND));
    }


}
