package com.paprocki.Zarzadzanie.Lekcjami.services;

import com.paprocki.Zarzadzanie.Lekcjami.model.Student;
import com.paprocki.Zarzadzanie.Lekcjami.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public Optional<Student> getSingleStudent(String email) {
        return studentRepository.findByEmail(email);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> editStudent(Student updatedStudent) {
        Optional<Student> student = studentRepository.findByEmail(updatedStudent.getEmail());
        if (student.isPresent()) {
            Student student1 = studentRepository.updateStudent(student.get());
            return Optional.of(student1);
        } else {
            return Optional.empty();
        }
    }

    public Optional<Student> editStudentPartially(Student updatedStudent) {
        String email = updatedStudent.getEmail();
        Optional<Student> student = studentRepository.findByEmail(email);
        if (student.isPresent()) {
            Student student1 = studentRepository.partiallyUpdateStudent(student.get());
            return Optional.of(student1);
        } else {
            return Optional.empty();
        }
    }

    public boolean addStudent(Student student) {
        Optional<Student> optionalStudent = studentRepository.findByEmail(student.getEmail());
        if (optionalStudent.isPresent()) {
            return false;
        } else {
            return studentRepository.add(student);
        }
    }

}

// C <-> S <-> R
