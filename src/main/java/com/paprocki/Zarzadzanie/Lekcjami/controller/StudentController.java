package com.paprocki.Zarzadzanie.Lekcjami.controller;

import com.paprocki.Zarzadzanie.Lekcjami.model.Student;
import com.paprocki.Zarzadzanie.Lekcjami.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
    }

    @GetMapping("/{email}")
    public ResponseEntity<Student> getSingleStudent(@PathVariable String email) {
        return studentService.getSingleStudent(email)
                .map(student -> new ResponseEntity<>(student, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity("Brak studenta o danym emailu " + email, HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        boolean result = studentService.addStudent(student);
        if (result) {
            return new ResponseEntity<>(student, HttpStatus.CREATED);
        } else {
            return new ResponseEntity("Konto z takim email już istnieje", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping()
    public ResponseEntity<Student> editStudent(@RequestBody Student updatedStudent) {
        return studentService.editStudent(updatedStudent)
                .map(s -> new ResponseEntity<>(s, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity("Brak studenta o danym emailu " + updatedStudent.getEmail(), HttpStatus.NOT_FOUND));
    }


    @PatchMapping
    public ResponseEntity<Student> editStudentPartially(@RequestBody Student updatedStudent) {
        return studentService.editStudentPartially(updatedStudent)
                .map(s -> new ResponseEntity<>(s, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity("Brak studenta o danym emailu " + updatedStudent.getEmail(), HttpStatus.NOT_FOUND));
    }


}
