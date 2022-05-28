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

    public Student updateStudent(Student student) {
        Student foundedStudent = findByEmail(student.getEmail()).get();
        foundedStudent.setEmail(student.getEmail());
        foundedStudent.setName(student.getName());
        foundedStudent.setTeacher(student.getTeacher());
        foundedStudent.setRate(student.getRate());
        return foundedStudent;
    }

    public Student partiallyUpdateStudent(Student student) {
        Student newStudent = students.stream()
                .filter(s -> s.getEmail().equals(student.getEmail()))
                .findAny()
                .get();
        Optional.ofNullable(student.getEmail()).ifPresent(newStudent::setEmail);
        Optional.ofNullable(student.getName()).ifPresent(newStudent::setEmail);
        Optional.ofNullable(student.getRate()).ifPresent(newStudent::setRate);
        Optional.ofNullable(student.getTeacher()).ifPresent(newStudent::setTeacher);
        return newStudent;
    }

    public boolean add(Student student) {
        return students.add(student);
    }
}
