package com.paprocki.Zarzadzanie.Lekcjami.repository;

import com.paprocki.Zarzadzanie.Lekcjami.model.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TeacherRepository {

    public List<Teacher> teachers = new ArrayList<>();

    @PostConstruct
    public void init() {
        teachers.add(new Teacher("Arkadiusz Domański", "arkadiusz.domanski@gmail.com"));
        teachers.add(new Teacher("Zbigniew Wodzimski", "zgigniew.wodzimski@gmail.com"));
    }

    public Optional<Teacher> findTeacherByEmail(String email) {
        return teachers.stream()
                .filter(teacher -> teacher.getEmail().equals(email))
                .findAny();
    }

    public List<Teacher> getAll() {
        return teachers;
    }

    public Optional<Teacher> updateTeacher(Teacher teacher) {
        Optional<Teacher> optionalTeacher = findTeacherByEmail(teacher.getEmail());
        if (optionalTeacher.isPresent()) {
            Teacher foundedTeacher = optionalTeacher.get();
            foundedTeacher.setName(teacher.getName());
            foundedTeacher.setEmail(teacher.getEmail());
        }
        return optionalTeacher;
    }

    public boolean addTeacher(Teacher teacher) {
        return teachers.add(teacher);
    }

    public Optional<Teacher> partiallyUpdateTeacher(Teacher teacher) {
        Optional<Teacher> optionalTeacher = findTeacherByEmail(teacher.getEmail());
        if (optionalTeacher.isPresent()) {
            Teacher updatedTeacher = optionalTeacher.get();
            Optional.ofNullable(teacher.getEmail()).ifPresent(updatedTeacher::setEmail);
            Optional.ofNullable(teacher.getName()).ifPresent(updatedTeacher::setName);
        }
        return optionalTeacher;
    }


}
