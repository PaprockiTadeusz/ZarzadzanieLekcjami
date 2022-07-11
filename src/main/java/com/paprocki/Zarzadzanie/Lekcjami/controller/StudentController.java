package com.paprocki.Zarzadzanie.Lekcjami.controller;

import com.paprocki.Zarzadzanie.Lekcjami.dto.StudentDTO;
import com.paprocki.Zarzadzanie.Lekcjami.exceptions.EmailUsedExecption;
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
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
    }

    @GetMapping("/{email}")
    public ResponseEntity<StudentDTO> getSingleStudent(@PathVariable String email) {
        return studentService.getSingleStudent(email)
                .map(studentDTO -> new ResponseEntity<>(studentDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity("Brak studenta o danym emailu " + email, HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<StudentDTO> addStudent(@RequestBody StudentDTO studentDTO) throws EmailUsedExecption {
        boolean result = studentService.addStudent(studentDTO);
        if (!result) {
            throw new EmailUsedExecption("Student z takim emailem istnieje", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(studentDTO, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<StudentDTO> editStudent(@RequestBody StudentDTO updatedStudentDTO) {
        return studentService.editStudent(updatedStudentDTO)
                .map(s -> new ResponseEntity<>(s, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity("Brak studenta o danym emailu " + updatedStudentDTO.getEmail(), HttpStatus.NOT_FOUND));
    }


    @PatchMapping
    public ResponseEntity<StudentDTO> editStudentPartially(@RequestBody StudentDTO updatedStudentDTO) {
        return studentService.editStudentPartially(updatedStudentDTO)
                .map(s -> new ResponseEntity<>(s, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity("Brak studenta o danym emailu " + updatedStudentDTO.getEmail(), HttpStatus.NOT_FOUND));
    }


}
