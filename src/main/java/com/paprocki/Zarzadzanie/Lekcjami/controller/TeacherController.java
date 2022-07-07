package com.paprocki.Zarzadzanie.Lekcjami.controller;

import com.paprocki.Zarzadzanie.Lekcjami.dto.TeacherDTO;
import com.paprocki.Zarzadzanie.Lekcjami.enitites.TeacherEntity;
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

    @GetMapping
    public ResponseEntity getAllTeachers() {
        return new ResponseEntity(teacherService.getAllTeachers(), HttpStatus.OK);
    }

    @GetMapping("/{email}")
    public ResponseEntity getOneTeacher(@PathVariable String email) {
        Optional<TeacherDTO> newTeacher = teacherService.getSingleTeacher(email);
        return newTeacher.map(teacherDTO -> new ResponseEntity(teacherDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity("Brak nauczyciela o danym emailu " + email, HttpStatus.BAD_REQUEST));
    }

    @PostMapping
    public ResponseEntity addTeacher(@RequestBody TeacherDTO teacherDTO) {
        if (teacherService.addNewTeacher(teacherDTO)) {
            return new ResponseEntity<>(teacherDTO, HttpStatus.CREATED);
        } else {
            return new ResponseEntity("Konto z takim emailem już istnieje", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping()
    public ResponseEntity editTeacher(@RequestBody TeacherDTO updatedTeacherDTO) {
        return teacherService.editTeacher(updatedTeacherDTO)
                .map(t -> new ResponseEntity<>(t, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity("Brak nauczyciela o danym mailu " + updatedTeacherDTO.getEmail(), HttpStatus.NOT_FOUND));
    }

    @PatchMapping
    public ResponseEntity editTeacherPartially(@RequestBody TeacherDTO updatedTeacherDTO) {
        return teacherService.editTeacherPartially(updatedTeacherDTO)
                .map(t -> new ResponseEntity<>(t, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity("Brak nauczyciela o danym mailu " + updatedTeacherDTO.getEmail(), HttpStatus.NOT_FOUND));
    }


}
