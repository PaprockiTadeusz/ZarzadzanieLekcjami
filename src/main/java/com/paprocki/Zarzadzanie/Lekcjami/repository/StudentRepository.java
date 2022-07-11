package com.paprocki.Zarzadzanie.Lekcjami.repository;

import com.paprocki.Zarzadzanie.Lekcjami.dto.StudentDTO;
import com.paprocki.Zarzadzanie.Lekcjami.enitites.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    Optional<StudentEntity> findByEmail(String email);


//    public List<StudentDTO> studentDTOS = new ArrayList<>();
//
//    @PostConstruct
//    public void init() {
//        studentDTOS.add(new StudentDTO("Mirosław Kowalski", "mirek@gmail.com", "Michał Leja", 200));
//        studentDTOS.add(new StudentDTO("Jakub Nowicki", "jakub8899wp.pl", "Michał Leja", 200));
//    }
//
//    public Optional<StudentDTO> findByEmail(String email) {
//        return studentDTOS.stream()
//                .filter(studentDTO -> studentDTO.getEmail().equals(email))
//                .findAny();
//    }
//
//    public List<StudentDTO> findAll() {
//        return studentDTOS;
//    }
//
//    public Optional<StudentDTO> updateStudent(StudentDTO studentDTO) {
//        Optional<StudentDTO> optionalStudent = findByEmail(studentDTO.getEmail());
//        if (optionalStudent.isPresent()) {
//            StudentDTO foundedStudentDTO = optionalStudent.get();
//            foundedStudentDTO.setName(studentDTO.getName());
//            foundedStudentDTO.setTeacher(studentDTO.getTeacher());
//            foundedStudentDTO.setRate(studentDTO.getRate());
//        }
//        return optionalStudent;
//    }
//
//    public Optional<StudentDTO> partiallyUpdateStudent(StudentDTO studentDTO) {
//        Optional<StudentDTO> optionalStudent = findByEmail(studentDTO.getEmail());
//        if (optionalStudent.isPresent()) {
//            StudentDTO updatedStudentDTO = optionalStudent.get();
//            Optional.ofNullable(studentDTO.getName()).ifPresent(updatedStudentDTO::setName);
//            Optional.ofNullable(studentDTO.getRate()).ifPresent(updatedStudentDTO::setRate);
//            Optional.ofNullable(studentDTO.getTeacher()).ifPresent(updatedStudentDTO::setTeacher);
//        }
//        return optionalStudent;
//    }
//
//    public boolean add(StudentDTO studentDTO) {
//        return studentDTOS.add(studentDTO);
//    }
}
