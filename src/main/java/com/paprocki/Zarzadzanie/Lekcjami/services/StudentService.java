package com.paprocki.Zarzadzanie.Lekcjami.services;

import com.paprocki.Zarzadzanie.Lekcjami.dto.StudentDTO;
import com.paprocki.Zarzadzanie.Lekcjami.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public Optional<StudentDTO> getSingleStudent(String email) {
        return studentRepository.findByEmail(email);
    }

    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<StudentDTO> editStudent(StudentDTO updatedStudentDTO) {
        return studentRepository.updateStudent(updatedStudentDTO);
    }

    public Optional<StudentDTO> editStudentPartially(StudentDTO updatedStudentDTO) {
        return studentRepository.partiallyUpdateStudent(updatedStudentDTO);
    }

    public boolean addStudent(StudentDTO studentDTO) {
        Optional<StudentDTO> optionalStudent = studentRepository.findByEmail(studentDTO.getEmail());
        if (optionalStudent.isPresent()) {
            return false;
        } else {
            return studentRepository.add(studentDTO);
        }
    }

}

// C <-> S <-> R
