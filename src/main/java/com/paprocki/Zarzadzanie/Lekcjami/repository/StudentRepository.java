package com.paprocki.Zarzadzanie.Lekcjami.repository;

import com.paprocki.Zarzadzanie.Lekcjami.model.Student;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepository {

    public List<Student> students = new ArrayList<>();

    @PostConstruct
    public void init() {
        students.add(new Student("Mirosław Kowalski", "mirek@gmail.com", "Michał Leja", 200));
        students.add(new Student("Jakub Nowicki", "jakub8899wp.pl", "Michał Leja", 200));
    }

    public Optional<Student> findByEmail(String email) {
        return students.stream()
                .filter(student -> student.getEmail().equals(email))
                .findAny();
    }

    public List<Student> findAll(){
        return students;
    }

    public Optional<Student> updateStudent(Student student) {
        Optional<Student> optionalStudent = findByEmail(student.getEmail());
        if (optionalStudent.isPresent()) {
            Student foundedStudent = optionalStudent.get();
            foundedStudent.setName(student.getName());
            foundedStudent.setTeacher(student.getTeacher());
            foundedStudent.setRate(student.getRate());
        }
        return optionalStudent;
    }

    public Optional<Student> partiallyUpdateStudent(Student student) {
        Optional<Student> optionalStudent = findByEmail(student.getEmail());
        if (optionalStudent.isPresent()) {
            Student updatedStudent = optionalStudent.get();
            Optional.ofNullable(student.getName()).ifPresent(updatedStudent::setName);
            Optional.ofNullable(student.getRate()).ifPresent(updatedStudent::setRate);
            Optional.ofNullable(student.getTeacher()).ifPresent(updatedStudent::setTeacher);
        }
        return optionalStudent;
    }

    public boolean add(Student student) {
        return students.add(student);
    }
}
