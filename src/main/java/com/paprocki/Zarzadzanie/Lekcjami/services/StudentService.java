package com.paprocki.Zarzadzanie.Lekcjami.services;

import com.paprocki.Zarzadzanie.Lekcjami.dto.StudentDTO;
import com.paprocki.Zarzadzanie.Lekcjami.enitites.StudentEntity;
import com.paprocki.Zarzadzanie.Lekcjami.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    public Optional<StudentDTO> getSingleStudent(String email) {
        return studentRepository.findByEmail(email)
                .map(o -> modelMapper.map(o, StudentDTO.class));
    }

    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(o -> modelMapper.map(o, StudentDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<StudentDTO> editStudent(StudentDTO updatedStudentDTO) {
        StudentEntity save = studentRepository.save(modelMapper.map(updatedStudentDTO, StudentEntity.class));
        return Optional.of(modelMapper.map(save, StudentDTO.class));
    }

    public Optional<StudentDTO> editStudentPartially(StudentDTO updatedStudentDTO) {
        return studentRepository.findByEmail(updatedStudentDTO.getEmail())
                .map(entity -> {
                    Optional.of(updatedStudentDTO.getName()).ifPresent(entity::setName);
                    Optional.of(updatedStudentDTO.getEmail()).ifPresent(entity::setEmail);
                    Optional.of(updatedStudentDTO.getRate()).ifPresent(entity::setRate);
                    Optional.of(updatedStudentDTO.getTeacher()).ifPresent(entity::setTeacher);
                    Optional.of(updatedStudentDTO.getLessons()).ifPresent(entity::setLessons);
                    StudentEntity save = studentRepository.save(entity);
                    return Optional.of(modelMapper.map(save, StudentDTO.class));
                })
                .orElseGet(Optional::empty);
    }

    public boolean addStudent(StudentDTO studentDTO) {
        if (studentRepository.findByEmail(studentDTO.getEmail()).isEmpty()) {
            studentRepository.save(modelMapper.map(studentDTO, StudentEntity.class));
            return true;
        }
        return false;
    }
}